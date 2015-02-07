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
public class ex17 {
	static char s[];
	static char data[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
	static int i = 0;
	static void pn(int n) {
		if(n != 0) {
			s[i] = data[n % 10];
			i++;
			pn(n / 10);			
		}
	}
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("请输入整数: ");
		int n = scan.nextInt();
		s = new char[1000];
		pn(n);
		for(int i = s.length - 1; i >= 0; i--) {
			System.out.print("" + s[i]);
		}
		System.out.print("\n");
	}
}
