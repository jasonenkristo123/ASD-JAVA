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
//         } else if (head.equals(tail)) {
//             head = tail = null;
//         } else if (head.data.equals(key)) {
//             head = head.next;
//             head.prev = null;
//         } else if (tail.data.equals(key)) {
//             tail = tail.prev;
//             tail.next = null;
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

//         if (head.equals(tail)) {
//             addLast(data);
//         } else if (head.data.equals(key)) { 
//             newNode.next = head.next;
//             if (head.next != null) {
//                 head.next.prev = newNode;
//             }
//             head.next = newNode;
//             newNode.prev = head;
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
//             } else if (temp != null) { 
//                 addLast(data);
//                 return;
//             } else {
//                 System.out.println("Data Key Tidak Ditemukan");
//                 return;
//             }
//         }
//         size++;
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

//     void display() {
//         Node temp = head;

//         while (temp != null) {
//             System.out.print(temp.data + " <-> ");
//             temp = temp.next;
//         }

//         System.out.println("null");
//     }
// }

// class DDLMain {
//     public static void main(String[] args) {
//         DDL list = new DDL();

//         Mahasiswa mhs1 = new Mahasiswa("2023001", "Andi", 3.2);
//         Mahasiswa mhs2 = new Mahasiswa("2023002", "Budi", 3.8);
//         Mahasiswa mhs3 = new Mahasiswa("2023003", "Citra", 3.5);
//         Mahasiswa mhs4 = new Mahasiswa("2023004", "Diana", 4.0);

//         list.addFirst(mhs1);
//         list.addLast(mhs2);
//         list.addLast(mhs4);

//         list.insertAfter(mhs2, mhs3);

//         System.out.println("Isi Double Linked List Mahasiswa:");
//         list.display();
        
//         System.out.println("Ukuran list: " + list.size());
        
//         list.delete(mhs1); 
//         System.out.println("\nSetelah mhs1 (Andi) dihapus:");
//         list.display();
//         System.out.println("Apakah mhs2 (Budi) ada di list? " + list.search(mhs2));
//     }
// }