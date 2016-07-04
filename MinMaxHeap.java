package assignment3;

public class MinMaxHeap {
	// DO NOT CHANGE THESE VARIABLES AND METHODS
	private int currentSize;
	private int[] arr;
	
	public MinMaxHeap(int capacity){//Constructor
	arr = new int[capacity + 1];
	currentSize = 0;
	}
	
	public boolean isFull(){return currentSize == arr.length - 1;}
	
	public boolean isEmpty(){return currentSize == 0;}
	
	public void insert(int x){ //PRE: The heap is not full
		++currentSize;
		arr[currentSize] = x;
		BubbleUp(currentSize);
	}
	
	public int min() {//PRE: The heap is not empty
		return arr[1];
	}
	
	public int max(){ //PRE: The heap is not empty
		if (currentSize == 1) {
			return arr[1];
		}
		if (arr[2] < arr[3]) {
			return arr[3];
		}
		else {
			return arr[2];
		}
	}
	
	public int deleteMin(){ //PRE: The heap is not empty 
		int min = arr[1]; 
		arr[1] = arr[currentSize--];
		TrickleDown(1);
		return min;
	}
	
	public int deleteMax(){ //PRE: The heap is not empty
		int max;
		if (currentSize == 1) {
			return arr[currentSize--];
		}
		if (arr[2] < arr[3]) {
			max = arr[3];
			arr[3] = arr[currentSize--];
			TrickleDown(3);
		}
		else {
			max = arr[2];
			arr[2] = arr[currentSize--];
			TrickleDown(2);
		}
		return max;
	}
	
	private void BubbleUp(int node) {
		int level = (int) Math.floor((Math.log10(node)/(Math.log10(2))));
		if ((level % 2) == 0) { 													//if on a min level
			if (((node / 2) > 0) && (arr[node] > arr[node/2])) {					// if node has a parent
				int tmp = arr[node/2];												//swap
				arr[node/2] = arr[node];
				arr[node] = tmp;
				BubbleUpMax(node/2);
			}
			else {
				BubbleUpMin(node);
			}
		}
		else {
			if (((node / 2) > 0) && (arr[node] < arr[node/2])) {					// if node has a parent
				int tmp = arr[node/2];												//swap
				arr[node/2] = arr[node];
				arr[node] = tmp;
				BubbleUpMin(node/2);
			}
			else {
				BubbleUpMax(node);
			}
		}
	}
	
	private void BubbleUpMin(int node) {
		if ((((node / 2)/2) > 0)) {													//if node has a grandparent
			if (arr[node] < arr[((node/2)/2)]) {
				int tmp = arr[((node/2)/2)];
				arr[((node/2)/2)] = arr[node];
				arr[node] = tmp;
				BubbleUpMin(((node/2)/2));
			}
		}
	}
	
	private void BubbleUpMax(int node) {
		if ((((node / 2)/2) > 0)) {													//if node has a grandparent
			if (arr[node] > arr[((node/2)/2)]) {
				int tmp = arr[((node/2)/2)];
				arr[((node/2)/2)] = arr[node];
				arr[node] = tmp;
				BubbleUpMax(((node/2)/2));
			}
		}
	}

	private void TrickleDown(int node) {
		int level = (int) Math.floor((Math.log10(node)/(Math.log10(2))));
		if ((level % 2) == 0) { 					//if on a min level
			TrickleDownMin(node);
		}
		else {
			TrickleDownMax(node);
		}
	}
	
	private void TrickleDownMin(int node) {
		if ((node * 2) <= currentSize) {			// if the node has children...
			int min = node;
			int tradeMin = node;
			if ((node * 4) <= currentSize) {		// if the node has grandchildren
				for (int i = (node * 4); (i <= ((node * 4) + 4)) && i <= currentSize; i++) {
					if (arr[min] > arr[i]) {
						if (arr[tradeMin] > arr[i]) {
							tradeMin = i;
						}
					}
				}
				int tmp = arr[tradeMin];
				arr[tradeMin] = arr[min];
				arr[min] = tmp;
				if (tradeMin == node) {
					return;
				}
				TrickleDownMin(tradeMin);
			}
		}
	}
	
	private void TrickleDownMax(int node) {
		if ((node * 2) <= currentSize) {			// if the node has children...
			int min = node;
			int tradeMin = node;
			if ((node * 4) <= currentSize) {		// if the node has grandchildren
				for (int i = (node * 4); (i <= ((node * 4) + 4)) && i <= currentSize; i++) {
					if (arr[min] < arr[i]) {
						if (arr[tradeMin] < arr[i]) {
							tradeMin = i;
						}
					}
				}
				int tmp = arr[tradeMin];
				arr[tradeMin] = arr[min];
				arr[min] = tmp;
				if (tradeMin == node) {
					return;
				}
				TrickleDownMax(tradeMin);
			}
		}
	}
}

