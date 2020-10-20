import java.lang.StringBuilder
import kotlin.random.Random

fun main(){

    var unit = 20000
    var sort = Sort()


    var column = mutableListOf<String>("Insertion Sort","Merge Sort","Heap Sort","Quick Sort")
    var sortArr = mutableListOf<((input:MutableList<Int>)->Unit)>()



    sortArr.add{
        sort.insertionSort(it)
    }
    sortArr.add{
        sort.mergeSort(it)
    }
    sortArr.add{
        sort.heapSort(it)
    }
    sortArr.add{
        sort.quickSort(it)
    }

    for (i in 0 until column.size){
        printSort(unit,column[i]){
            sortArr[i].invoke(it)
        }
    }

}

private fun printSort(
    unit: Int,
    column:String,
    sort:((input:MutableList<Int>)->Unit)
) {
    println(column)
    var time = System.currentTimeMillis()
    for (i in 1..3) {
        val curResult = mutableListOf<Double>()
        println("前 ${unit * i} 筆")
        var str = StringBuilder()
        for (j in 1..5) {
            str.append("l$j.txt\t")
            var input = object {}.javaClass.getResource("l$j.txt").readText().split(",").toTypedArray()
            var inputInt = input.asInts().toMutableList().subList(0, unit * i)
            time = System.currentTimeMillis()
            try {
                sort.invoke(inputInt)
                val duration = (System.currentTimeMillis() - time).toDouble() / 1000.0
                curResult.add(duration)
                str.append(duration)
                str.append(" 秒")
            } catch (e: StackOverflowError) {
                str.append("StackOverFlowError ")
                continue
            }

        }
        println(str.toString())
    }
}

fun arrToRandom(arr:MutableList<Int>){
    repeat(arr.size * 2){
        val rdn1 = Random.nextInt(arr.size-1)
        val rdn2 = Random.nextInt(arr.size-1)
        val temp = arr[rdn1]
        arr[rdn1] = arr[rdn2]
        arr[rdn2] = temp
    }
}

fun swap(list:MutableList<Int>){
    val temp = list[0]
    list[0] = list[1]
    list[1] = temp
}

fun Array<String>.asInts() = this.map { it.trim().toInt() }.toTypedArray()

fun testSort(){
    val sort = Sort()
    var tArr = mutableListOf<Int>()
    for(i in 1 .. 60000){
        tArr.add(i)
    }
    arrToRandom(tArr)
    println(tArr)
    sort.quickSort(tArr)
    println(tArr)
}