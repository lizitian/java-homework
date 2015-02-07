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
public class ex16 {
	static boolean isprime[];
	static void createPrime() {
		isprime = new boolean[1001];
		int i = 0, j = 0, s = 0;
		isprime[0] = false;
		isprime[1] = false;
		isprime[2] = true;
		isprime[3] = true;
		isprime[4] = false;
		isprime[5] = true;
		for(i = 5; i <= 1000; i++) {
			s = (int)Math.floor(Math.sqrt(i)) + 1;
			for(j = 2; j < s; j++)
				if(isprime[j] && (i % j == 0))
					break;
			if(j == s) isprime[i] = true;
			else isprime[i] = false;
		}
	}
	public static void main(String[] args) {
		createPrime();
		int i, a, b, c;
		for(i = 100; i < 1000; i++) {
			a = i / 100;
			b = (i % 100) / 10;
			c = (i % 10);
			if(isprime[i] && isprime[100 * c + 10 * b + a])
				System.out.println("" + i);
		}
	}
}
