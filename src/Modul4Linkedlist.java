// public class Modul4Linkedlist {
// }

// class Mahasiswa {
//     String nim;
//     String nama;
//     double ipk;

//     public Mahasiswa(String nim, String nama, double ipk) {
//         this.nim = nim;
//         this.nama = nama;
//         this.ipk = ipk;
//     }

//     public double getIpk() {
//         return ipk;
//     }

//     public String getNim() {
//         return nim;
//     }

//     public String getNama() {
//         return nama;
//     }

//     @Override
//     public String toString() {
//         return "NIM: " + nim + ", Nama: " + nama + ", IPK: " + ipk;
//     }
// }

// class Node {
//     Object data;
//     Node next;

//     public Node(Object data) {
//         this.data = data;
//         this.next = null;
//     }
// }

// class SSL {
//     Node head, tail;
//     int size;

//     void inisialisasi() {
//         head = null;
//     }
    
//     boolean isEmpty() {
//         return (size == 0);
//     }

//     int size() {
//         return size;
//     }

//     void addFirst(Node input) {
//         if (isEmpty()) {
//             head = input;
//             tail = input;
//         } else {
//             input.next = head;
//             head = input;
//         }
//         size++;
//     }

//     void addLast(Node input) {
//         if (isEmpty()) {
//             head = input;
//             tail = input;
//         } else {
//             tail.next = input;
//             tail = input;
//         }

//         size++;
//     }

//     void deleteFirst() {
//         if (isEmpty()) {
//             System.out.println("Kosong");
//             return;
//         } else if (head == tail) {
//             head = tail = null;
//         } else {
//             head = head.next;
//         }
//         size--;
//      }

//      void deleteLast() {
//         if (isEmpty()) {
//             System.out.println("Kosong");
//             return;
//         } if (head == tail) {
//             head = tail = null;
//         } else {
//             Node temp = head;
//             while (temp.next != tail) {
//                 temp = temp.next;
//             }

//             tail = temp;
//             tail.next = null;
//         }
//      }

//      void insertAfter(Object key, Object data) {
//         Node temp = head;
//         Node newNode = new Node(data);

//         while (temp != null) {
//             if (temp.data.equals(key)) {
//                 newNode.next = temp.next;
//                 temp.next = newNode;

//                 if (newNode.next == null) {
//                     tail = newNode;
//                 }
//                 size++;
//             }
//             temp = temp.next;
//         }
//         System.out.println("key tidak ditemukan");
//      } 

//     boolean search(Object key) {
//         Node temp = head;

//         while (temp != null) {
//             if (temp.data.equals(key)) {
//                 return true;
//             }
//             temp = temp.next;
//         }
//         return false;
//     }

//     void display() {
//         Node temp = head;

//         while (temp != null) {
//             System.out.print(temp.data + ", ");
//             temp = temp.next;
//         }

//         System.out.println();
//     }

//     void insertSortedByIpk(Mahasiswa mhs) {
//         Node newNode = new Node(mhs);

//         if (isEmpty()) {
//             head = tail = newNode;
//             size++;
//             return;
//         }

//         Mahasiswa headData = (Mahasiswa) head.data;

//         if (headData.getIpk() > mhs.getIpk()) {
//             newNode.next = head;
//             head = newNode;
//             size++;
//             return;
//         }
        
//         Node temp = head;

//         while (temp.next != null && ((Mahasiswa) temp.next.data).getIpk() < mhs.getIpk()) {
//             temp = temp.next;
//         }

//         newNode.next = temp.next;
//         temp.next = newNode;

//         if (newNode.next == null) {
//             tail = newNode;
//         }

//         size++;
//     }
// }


// class MainList {
//     public static void main(String[] args) {
//         SSL list = new SSL();

//         list.insertSortedByIpk(new Mahasiswa("2023001", "Andi", 3.2));
//         list.insertSortedByIpk(new Mahasiswa("2023002", "Budi", 3.8));
//         list.insertSortedByIpk(new Mahasiswa("2023003", "Citra", 3.5));
//         list.insertSortedByIpk(new Mahasiswa("2023004", "Diana", 4.0));

//         list.display();

//     }
// }
