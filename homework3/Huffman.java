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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
class Huffman {
    public static void main(String[] args) throws IOException {
        long length = new File("input.txt").length();
        if(length == 0) {
            System.out.println("I/O Error.");
            return;
        }
        FileInputStream fis = new FileInputStream("input.txt");
        int i = (int)length, j;
        byte[] data = new byte[i];
        for(j = 0; (i = fis.read()) != -1 && j < length; j++) {
            if(i >= (int)'a' && i <= (int)'z')
                data[j] = (byte)i;
            if(i >= (int)'A' && i <= (int)'Z')
                data[j] = (byte)(i + (int)'a' - (int)'A');
        }
        if(j != length) {
            System.out.println("I/O Error.");
            return;
        }
        fis.close();
        byte[] code = HuffmanEncode(data);
        FileOutputStream fos = new FileOutputStream("output.huf");
        for(byte b : code)
            fos.write(b);
        fos.close();
    }
    private static byte[] HuffmanEncode(byte[] data) {
        byte[] code;
        Info info = CalWeight(data);
        if(info.dic.length == 1) {
            System.out.println("This file has only one line.");
            return null;
        }
        CalHuffmanCode(info);
        int en_len = 0;
        for(int i = 0; i < info.dic.length; i++)
            en_len += info.weight[i] * info.hc[i].length;
        boolean[] cs;
        cs = ToStream(data, info, en_len);
        code = ToBinary(cs);
        return code;
    }
    private static Info CalWeight(byte[] data) {
        Info info = new Info();
        int[] tmp_weight = new int[26];
        for(byte i : data)
            tmp_weight[i - (int)'a']++;
        int dic_len = 0;
        for(int i : tmp_weight)
            if(i != 0)
                dic_len++;
        info.dic = new byte[dic_len];
        info.weight = new int[dic_len];
        dic_len = 0;
        for(int i = 0; i < 26; i++)
            if(tmp_weight[i] != 0) {
                info.dic[dic_len] = (byte)(i + (int)'a');
                info.weight[dic_len++] = tmp_weight[i];
            }
        return info;
    }
    private static void CalHuffmanCode(Info info) {
        HuffmanTree[] ht = new HuffmanTree[2 * info.dic.length];
        for(int i = 0; i < 2 * info.dic.length; i++)
            ht[i] = new HuffmanTree();
        ht[0].weight = ht[0].parent = ht[0].lchild = ht[0].rchild = 0;
        for(int i = 1; i <= info.dic.length; i++) {
            ht[i].weight = info.weight[i - 1];
            ht[i].parent = ht[i].lchild = ht[i].rchild = 0;
        }
        int s1, s2, j;
        for(int i = info.dic.length + 1; i < 2 * info.dic.length; i++) {
            s1 = s2 = 0;
            for(j = 1; j < i; j++)
                if(ht[j].parent == 0) {
                    if(s1 == 0)
                        s1 = j;
                    else {
                        s2 = j;
                        break;
                    }
                }
            if(ht[s1].weight > ht[s2].weight) {
                int t = s1;
                s1 = s2;
                s2 = t;
            }
            for(j++; j < i; j++)
                if(ht[j].parent == 0 && ht[j].weight < ht[s2].weight) {
                    s2 = j;
                    if(ht[s1].weight > ht[s2].weight) {
                        int t = s1;
                        s1 = s2;
                        s2 = t;
                    }
                }
            ht[s1].parent = ht[s2].parent = i;
            ht[i].lchild = s1;
            ht[i].rchild = s2;
            ht[i].weight = ht[s1].weight + ht[s2].weight;
            ht[i].parent = 0;
        }
        boolean[] cd = new boolean[info.dic.length];
        info.hc = new boolean[info.dic.length][];
        for(int i = 1; i <= info.dic.length; i++) {
            int start = info.dic.length;
            for(s1 = i, s2 = ht[i].parent; s2 != 0; s1 = s2, s2 = ht[s2].parent)
                if(ht[s2].lchild == s1)
                    cd[--start] = false;
                else
                    cd[--start] = true;
            info.hc[i - 1] = new boolean[info.dic.length - start];
            for(j = 0; j < info.dic.length - start; j++)
                info.hc[i - 1][j] = cd[start + j];
        }
    }
    private static boolean[] ToStream(byte[] data, Info info, int en_len) {
        boolean[] code = new boolean[en_len];
        boolean[][] hc_work = new boolean[26][];
        int i, j;
        for(i = 0; i < info.dic.length; i++) {
            hc_work[info.dic[i] - (int)'a'] = new boolean[info.hc[i].length];
            for(j = 0; j < info.hc[i].length; j++)
                hc_work[info.dic[i] - (int)'a'][j] = info.hc[i][j];
        }
        int p = 0;
        for(i = 0; i < data.length; i++) {
            int len = hc_work[(int)data[i] - (int)'a'].length;
            for(j = 0; j < len; j++)
                code[p + j] = hc_work[(int)data[i] - (int)'a'][j];
            p += len;
        }
        return code;
    }
    private static byte[] ToBinary(boolean[] cs) {
        byte[] code = new byte[(cs.length - 1) / 8 + 1];
        int i = 0, j;
        for(j = 0; i < code.length; i++, j += 8) {
            byte c = 0;
            for(int k = 0; k < 8; k++) {
                c <<= 1;
                if(j + k == cs.length) {
                    c <<= 7 - k;
                    break;
                } else if(cs[j + k])
                    c |= 1;
            }
            code[i] = c;
        }
        return code;
    }
}
class Info {
    public byte[] dic;
    public int[] weight;
    public boolean[][] hc;
    public Info() {}
}
class HuffmanTree {
    public int weight, parent, lchild, rchild;
    public HuffmanTree() {}
}
