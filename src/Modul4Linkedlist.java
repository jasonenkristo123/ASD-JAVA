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
//         return "[NIM: " + nim + ", Nama: " + nama + ", IPK: " + ipk + "]";
//     }
// }

// class Node {
//     Object data;
//     Node next;
//     Node prev;

//     public Node(Object data) {
//         this.data = data;
//         this.next = null;
//         this.prev = null;
//     }
// }

// class DDL {
//     Node head, tail;
//     int size = 0;

//     void inisialisasi() {
//         this.head = null;
//         this.tail = null;
//     }

//     public DDL() {
//         inisialisasi();
//     }

//     boolean isEmpty() {
//         return (size == 0);
//     }

//     int size() {
//         return size;
//     }

//     void addFirst(Object data) {
//         Node newNode = new Node(data);

//         if (isEmpty()) {
//             head = tail = newNode;
//         } else {
//             newNode.next = head;
//             head.prev = newNode;
//             head = newNode;
//         }
//         size++;
//     }

//     void addLast(Object data) {
//         Node newNode = new Node(data);

//         if (isEmpty()) {
//             head = tail = newNode;
//         } else {
//             tail.next = newNode;
//             newNode.prev = tail;
//             tail = newNode;
//         }
//         size++;
//     }

//     void delete(Object key) {
//         if (isEmpty()) {
//             System.out.println("kosong");
//             return;
//         } else if (head == tail) {
//             head = tail = null;
//         } else if (head.data.equals(key)) {
//             head = head.next;
//             if (head != null) {
//                 head.prev = null;
//             }
//         } else if (tail.data.equals(key)) {
//             tail = tail.prev;
//             if (tail != null) {
//                 tail.next = null;
//             }
//         } else {
//             Node temp = head;

//             while (temp != null && !temp.data.equals(key)) {
//                 temp = temp.next;
//             }

//             if (temp != null) {
//                 temp.prev.next = temp.next;
//                 if (temp.next != null) {
//                     temp.next.prev = temp.prev;
//                 }
//             } else {
//                 System.out.println("No Data");
//                 return;
//             }
//         }
//         size--;
//     }

//     void insertAfter(Object key, Object data) {
//         if (isEmpty()) {
//             System.out.println("Kosong");
//             return;
//         }

//         Node newNode = new Node(data);
//         Node temp = head;

//         if (head.data.equals(key)) {
//             newNode.next = head.next;
//             if (head.next != null) {
//                 head.next.prev = newNode;
//             } else {
//                 tail = newNode;
//             }
//             head.next = newNode;
//             newNode.prev = head;
//             size++;
//         } else if (tail.data.equals(key)) {
//             addLast(data);
//         } else {
//             while (temp != null && !temp.data.equals(key)) {
//                 temp = temp.next;
//             }

//             if (temp != null && temp.next != null) {
//                 newNode.next = temp.next;
//                 temp.next.prev = newNode;
//                 temp.next = newNode;
//                 newNode.prev = temp;
//                 size++;
//             } else if (temp != null) {
//                 addLast(data);
//             } else {
//                 System.out.println("Data Key Tidak Ditemukan");
//             }
//         }
//     }

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
//             head.prev = newNode;
//             head = newNode;
//             size++;
//             return;
//         }

//         Node temp = head;

//         while (temp.next != null && ((Mahasiswa) temp.next.data).getIpk() < mhs.getIpk()) {
//             temp = temp.next;
//         }

//         newNode.next = temp.next;
//         newNode.prev = temp;

//         if (temp.next != null) {
//             temp.next.prev = newNode;
//         } else {
//             tail = newNode;
//         }

//         temp.next = newNode;
//         size++;
//     }

//     void displayAscending() {
//         Node temp = head;
//         while (temp != null) {
//             System.out.print(temp.data + " <-> ");
//             temp = temp.next;
//         }
//         System.out.println("null");
//     }

//     void displayDescending() {
//         Node temp = tail;
//         while (temp != null) {
//             System.out.print(temp.data + " <-> ");
//             temp = temp.prev;
//         }
//         System.out.println("null");
//     }
// }

// class DDLMain {
//     public static void main(String[] args) {
//         DDL list = new DDL();

//         list.insertSortedByIpk(new Mahasiswa("2023001", "Andi", 3.2));
//         list.insertSortedByIpk(new Mahasiswa("2023002", "Budi", 3.8));
//         list.insertSortedByIpk(new Mahasiswa("2023003", "Citra", 3.5));
//         list.insertSortedByIpk(new Mahasiswa("2023004", "Diana", 4.0));
//         list.insertSortedByIpk(new Mahasiswa("2023005", "Eka", 3.0));

//         System.out.println("Tampilan Ascending:");
//         list.displayAscending();

//         System.out.println("\nTampilan Descending:");
//         list.displayDescending();

//         System.out.println("\nUkuran list: " + list.size());
//     }
// }