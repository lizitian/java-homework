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
import java.math.BigDecimal;
public class pj {
	public static void main(String[] args) {
        BigDecimal f = new BigDecimal(0);
		for(int i = 10000000; i > 0; i -= 2) {
            double rate = 100 - i / 100000.0;
			f = new BigDecimal(1).divide(f.add(new BigDecimal(i)), (int)(rate * 400), BigDecimal.ROUND_HALF_UP);
            System.err.println(rate + "%");
        }
		System.out.println(new BigDecimal(1).divide(f, 30000, BigDecimal.ROUND_HALF_UP));
	}
}
