class NodeCSLL {
    Object data;
    NodeCSLL setelah;
}

public class CircularSingleLinkedList {
    private NodeCSLL pAwal;

    public CircularSingleLinkedList() {
        pAwal = null;
    }

    public void SisipDataDiAwal(Object data) {
        NodeCSLL pBaru = new NodeCSLL();
        pBaru.data = data;

        if (pAwal == null) {
            pAwal = pBaru;
            pBaru.setelah = pAwal;
        } else {
            NodeCSLL pBantu = pAwal;
            while (pBantu.setelah != pAwal) {
                pBantu = pBantu.setelah;
            }
            pBaru.setelah = pAwal;
            pAwal = pBaru; 
            pBantu.setelah = pAwal; 
        }
    }

    public void SisipDataDiAkhir(Object data) {
        NodeCSLL pBaru = new NodeCSLL();
        pBaru.data = data;
        
        if (pAwal == null) {
            pAwal = pBaru;
            pBaru.setelah = pAwal;
        } else {
            NodeCSLL pBantu = pAwal;
            while (pBantu.setelah != pAwal) {
                pBantu = pBantu.setelah;
            }
            pBantu.setelah = pBaru;
            pBaru.setelah = pAwal;
        }
    }

    public void hapusData(Object dtHapus) {
        if (pAwal != null) {
            NodeCSLL pSbl = null, pKini = pAwal;
            boolean ketemu = false;
            
            do {
                if (pKini.data.equals(dtHapus)) {
                    ketemu = true;
                    break;
                }
                pSbl = pKini;
                pKini = pKini.setelah;
            } while (pKini != pAwal);
            
            if (ketemu) {
                if (pKini == pAwal && pKini.setelah == pAwal) { 
                    pAwal = null;
                } else if (pKini == pAwal) { 
                    NodeCSLL pBantu = pAwal;
                    while (pBantu.setelah != pAwal) {
                        pBantu = pBantu.setelah;
                    }
                    pAwal = pAwal.setelah; 
                    pBantu.setelah = pAwal; 
                } else { 
                    pSbl.setelah = pKini.setelah;
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
        
        NodeCSLL pCetak = pAwal;
        do {
            System.out.print(pCetak.data + "->");
            pCetak = pCetak.setelah;
        } while (pCetak != pAwal);
        System.out.println();
    }
}