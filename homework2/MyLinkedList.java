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
class MyLinkedListNode {
    int data;
    MyLinkedListNode next;
    MyLinkedListNode(int i, MyLinkedListNode p) {
        data = i;
        next = p;
    }
}
class MyLinkedList {
    private MyLinkedListNode head;
    public MyLinkedList() {
        head = null;
    }
    public MyLinkedList(int[] dat) {
        for(int i : dat)
            head = new MyLinkedListNode(i, head);
    }
    public void Delete(int w) {
        if(head == null) return;
        while(head.data == w) head = head.next;
        MyLinkedListNode p = head;
        while(p.next != null)
            if(p.next.data == w)
                p.next = p.next.next;
            else
                p = p.next;
    }
    //Insert Node after node n and set data = w
    public void Insert(int w, int n) {
        if(n < 0) return;
        if(n == 0) {
            head = new MyLinkedListNode(w, head);
            return;
        }
        MyLinkedListNode p = head;
        for(int i = 1; i < n; i++) {
            if(p.next == null)
                return;
            p = p.next;
        }
        p.next = new MyLinkedListNode(w, p.next);
    }
    public void Print() {
        MyLinkedListNode p = head;
        System.out.println("data:");
        while(p != null) {
            System.out.println(p.data);
            p = p.next;
        }
    }
    public static void main(String[] args) {
        int[] a = {1,2,3,4,5};
        MyLinkedList p = new MyLinkedList(a);
        int c, t;
        java.util.Scanner scan = new java.util.Scanner(System.in);
        System.out.println("1 to ins, 2 to del, 0 to exit");
        while((c = scan.nextInt()) != 0) {
            if(c == 1) {
                System.out.println("input data and position");
                t = scan.nextInt();
                p.Insert(t, scan.nextInt());
            }
            if(c == 2) {
                System.out.println("input data to del");
                p.Delete(scan.nextInt());
            }
            p.Print();
            System.out.println("1 to add, 2 to del, 0 to exit");
        }
    }
}
