class NodeCDLL {
    Object data;
    NodeCDLL sebelum;
    NodeCDLL setelah;
}

public class CircularDoubleLinkedList {
    private NodeCDLL pAwal;

    public CircularDoubleLinkedList() {
        pAwal = null;
    }

    public void SisipDataDiAwal(Object data) {
        NodeCDLL pBaru = new NodeCDLL();
        pBaru.data = data;

        if (pAwal == null) {
            pAwal = pBaru;
            pBaru.sebelum = pBaru;
            pBaru.setelah = pBaru;
        } else {
            NodeCDLL pAkhirTemp = pAwal.sebelum; 
            
            pBaru.setelah = pAwal;
            pBaru.sebelum = pAkhirTemp;
            pAkhirTemp.setelah = pBaru;
            pAwal.sebelum = pBaru;
            
            pAwal = pBaru; 
        }
    }

    public void SisipDataDiAkhir(Object data) {
        NodeCDLL pBaru = new NodeCDLL();
        pBaru.data = data;
        
        if (pAwal == null) {
            pAwal = pBaru;
            pBaru.sebelum = pBaru;
            pBaru.setelah = pBaru;
        } else {
            NodeCDLL pAkhirTemp = pAwal.sebelum; 
            
            pBaru.sebelum = pAkhirTemp;
            pBaru.setelah = pAwal;
            pAkhirTemp.setelah = pBaru;
            pAwal.sebelum = pBaru;
        }
    }

    public void hapusData(Object dtHapus) {
        if (pAwal != null) {
            NodeCDLL pKini = pAwal;
            boolean ketemu = false;

            do {
                if (pKini.data.equals(dtHapus)) {
                    ketemu = true;
                    break;
                }
                pKini = pKini.setelah;
            } while (pKini != pAwal);

            if (ketemu) {
                if (pKini.setelah == pKini) { 
                    pAwal = null;
                } else {
                    pKini.sebelum.setelah = pKini.setelah;
                    pKini.setelah.sebelum = pKini.sebelum;
                    
                    if (pKini == pAwal) {
                        pAwal = pAwal.setelah;
                    }
                }
            }
        }
    }

    public void cetak(String Komentar) {
        System.out.println(Komentar);
        if (pAwal == null) {
            System.out.println("List Kosong");
            return;
        }
        
        NodeCDLL pCetak = pAwal;
        do {
            System.out.print(pCetak.data + "->");
            pCetak = pCetak.setelah;
        } while (pCetak != pAwal);
        System.out.println();
    }
}