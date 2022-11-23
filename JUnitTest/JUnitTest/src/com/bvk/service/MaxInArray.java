package com.bvk.service;

public class MaxInArray {

	public int findMax(int[] arr) {

		int max = 0;
		for (int i = 0; i < arr.length; i++) {
			max = Math.max(max, arr[i]);
		}

		return max;

	}

	public int findMax2(int[] arr) {

		// sorting array
		for (int i = 0; i < arr.length - 1; i++) {

			for (int j = i + 1; j < arr.length; j++) {

				if (arr[i] > arr[j]) {

					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;

				}
			}

		}

		return arr[arr.length - 1];

	}

}
