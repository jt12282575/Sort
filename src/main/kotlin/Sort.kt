class Sort {

    fun mergeSort(input:List<Int>):List<Int>{
        if (input.size <= 1) {
            return input
        }
        val middle = input.size/2
        var left = input.subList(0,middle)
        var right = input.subList(middle,input.size)
        return merge(mergeSort(left),mergeSort(right))
    }

    fun merge(left:List<Int>,right:List<Int>): List<Int> {
        val result = mutableListOf<Int>()
        var leftIndex = 0
        var rightIndex = 0
        while (leftIndex < left.size && rightIndex < right.size){
            if (left[leftIndex] < right[rightIndex]){
                result.add(left[leftIndex])
                leftIndex++
            }else{
                result.add(right[rightIndex])
                rightIndex++
            }
        }
        while (leftIndex<left.size){
            result.add(left[leftIndex])
            leftIndex++
        }
        while (rightIndex<right.size){
            result.add(right[rightIndex])
            rightIndex++
        }

        return result
    }

    fun insertionSort(result:MutableList<Int>){
        for (i in result.indices){
            var swapIndex = i
            var minNum = result[i]
            for (j in i until result.size){
                if (result[j] < minNum){
                    swapIndex = j
                    minNum = result[j]
                }
            }
            var temp =result[i]
            result[i] = result[swapIndex]
            result[swapIndex] = temp
        }
    }

    fun quickSort(input:MutableList<Int>, low:Int =0, high:Int = input.size-1){
        if (low >= high || high <= low){
        }else{
            val pivot = input[low]
            var i = low+1
            var temp = -1
            for (j in low+1 .. high){
                if (pivot > input[j]){
                    temp = input[j]
                    input[j] = input[i]
                    input[i] = temp
                    i++
                }
            }
            input[low] = input[i-1]
            input[i-1] = pivot
            quickSort(input,low,i-2)
            quickSort(input,i,high)
        }


    }


    fun heapSort(input:MutableList<Int>){
        buildMaxHeap(input)
        for (i in input.size -1 downTo 1){
            val temp = input[0]
            input[0] = input[i]
            input[i] = temp
            maxHeapify(input,0,i-1)
        }
    }

    private fun buildMaxHeap(input: MutableList<Int>){
        for (i in ((input.size /2) - 1) downTo 0){
            maxHeapify(input,i)
        }
    }

    private fun maxHeapify(input:MutableList<Int>,rootIndex:Int,heapSize:Int = input.size-1){
        val leftChildIndex = (2 * rootIndex ) +1
        val rightChildIndex = (2 * rootIndex ) +2
        var largestElementIndex = rootIndex

        if (leftChildIndex <= heapSize && input[leftChildIndex] > input[rootIndex]){
            largestElementIndex = leftChildIndex
        }

        if (rightChildIndex <= heapSize && input[rightChildIndex] > input[largestElementIndex]){
            largestElementIndex = rightChildIndex
        }

        if (largestElementIndex != rootIndex){
            val temp = input[rootIndex]
            input[rootIndex] = input[largestElementIndex]
            input[largestElementIndex] = temp
            maxHeapify(input,largestElementIndex,heapSize)
        }

    }



}