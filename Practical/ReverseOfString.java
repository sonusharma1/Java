package com.demo;

public class ReverseOfString {

	static void reverse(String str) {

		char[] arr = str.toCharArray();

		int size = arr.length - 1;

		for (int i = 0; i <= (arr.length / 2) - 1; i++) {
			char temp = arr[i];
			arr[i] = arr[size - i];
			arr[size - i] = temp;
		}
		str = String.copyValueOf(arr);
		System.out.println(str);
	}

	public static void main(String[] args) {
//		reverse("sonu");

		String str = "sk";
		str = str + "sk";
		System.out.println(str);
	}

}
