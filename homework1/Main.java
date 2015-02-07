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
class Main {

    private static final String[] wordArray = {"apple", "banana", "pear", "teacher", "what", "name", "school", "return", "java", "exit"};

    public static void main(String[] args) {
        String word = wordArray[new java.util.Random().nextInt(wordArray.length)];
        mainLoop(word);
        System.out.println("回答正确!");
    }

    private static void mainLoop(String word) {
        boolean[] input = new boolean[word.length()];
        char tmp;
        while(!finish(input)) {
            printWord(word, input);
            System.out.print("请输入要猜的字母,输入数字0来请求提示:");
            if((tmp = getInputChar()) == '\n') continue;
            if(tmp == '0')
                setChar(word, input, getRandomChar(word, input));
            else
                setChar(word, input, tmp);
        }
        printWord(word, input);
    }

    private static char getInputChar() {
        char c = '\n';
        try {
            c = (char)System.in.read();
            while((char)System.in.read() != '\n');
        } catch (java.io.IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return c;
    }

    private static boolean finish(boolean[] input) {
        for(boolean i : input)
            if(!i) return false;
        return true;
    }

    private static void printWord(String word, boolean[] input) {
        for(int i = 0; i < input.length; i++)
            if(input[i])
                System.out.print(word.charAt(i) + " ");
            else
                System.out.print("_ ");
        System.out.print('\n');
    }

    private static void setChar(String word, boolean[] input, char tmp) {
        for(int i = 0; i < input.length; i++)
            if(tmp == word.charAt(i))
                input[i] = true;
    }

    private static char getRandomChar(String word, boolean[] input) {
        char[] notGuess = new char[input.length];
        int j = 0;
        for(int i = 0; i < input.length; i++)
            if(!input[i] && !haveChar(notGuess, j, word.charAt(i)))
                notGuess[j++] = word.charAt(i);
        return notGuess[new java.util.Random().nextInt(j)];
    }

    private static boolean haveChar(char[] notGuess, int j, char c) {
        for(int i = 0; i < j; i++)
            if(notGuess[i] == c)
                return true;
        return false;
    }
}
