/* Copyright 2014 Zitian Li
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*
 * Author: Zitian Li
 * Email: me@zitianli.com
 * License: Apache License, Version 2.0
 */
import java.util.Scanner;
public class ex14 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("请输入一个十进制数: ");
		int a = scan.nextInt();
		System.out.print("请输入要转换的进制: ");
		int n = scan.nextInt();
		if(n < 2 || n > 36) {
			System.out.println("输入有误");
			return;
		}
		char src[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
		int i;
		char dest[] = new char[32];
		for(i = 0; i < dest.length; i++) {
			if(a == 0)
				break;
			else
				dest[i] = src[a % n];
			a /= n;
		}
		System.out.print("转换结果: ");
		for(n = i - 1; n >= 0; n--)
			System.out.print(dest[n]);
		System.out.println("");
	}
}
