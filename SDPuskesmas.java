package SDPuskesmas;

import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Scanner;

public class SDPuskesmas {

    private static String dokter() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static class Search<T> {
        private Set<T> searchedItems = new HashSet<>();

        public T search(T[] array, String keluhan, String poli) {
            for (T item : array) {
                if (!searchedItems.contains(item) && item instanceof dokter) {
                    dokter dokterItem = (dokter) item;
                    if (dokterItem.poli().equalsIgnoreCase(poli)) {
                        searchedItems.add(item);
                        return item;
                    }
                } else if (item instanceof pasien) {
                    pasien pasienItem = (pasien) item;
                    if (Objects.equals(pasienItem.keluhan(), keluhan)) {
                        return item;
                    }
                }
            }
            return null;
        }
    }

    public record pasien(String nama, int umur, int nik, String keluhan, String kategori) {}

    public record dokter(String nama, String poli, String ruangan, String jam) {}

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Daftar pasien yang akan berobat");
        pasien person1 = new pasien("siti", 25, 32416, "ginjal", "1.umum");
        pasien person2 = new pasien("neneng", 60, 32427, "jantung", "2.lansia");
        pasien person3 = new pasien("kiara", 4, 32418, "alegi", "3.balita");
        pasien person4 = new pasien("zaky", 21, 32425, "paru-paru", "4.bpjs");

        System.out.println("nama: " + person1.nama() + ", Umur: " + person1.umur() + ", nik: " + person1.nik() + ", Keluhan: " + person1.keluhan());
        System.out.println("nama: " + person2.nama() + ", Umur: " + person2.umur() + ", nik: " + person2.nik() + ", Keluhan: " + person2.keluhan());
        System.out.println("nama: " + person3.nama() + ", Umur: " + person3.umur() + ", nik: " + person3.nik() + ", Keluhan: " + person3.keluhan());
        System.out.println("nama: " + person4.nama() + ", Umur: " + person4.umur() + ", nik: " + person4.nik() + ", Keluhan: " + person4.keluhan() + "\n");

        dokter[] d = new dokter[5];
        d[0] = new dokter("dr farid", "ginjal", "gedung E", "8-10");
        d[1] = new dokter("dr rara", "jantung", "gedung C", "9-11");
        d[2] = new dokter("dr lily", "alergi", "gedung A", "11-12");     
        d[3] = new dokter("dr xena", "paru-paru", "gedung B", "13-14");
        d[4] = new dokter("dr satria", "spesialis mata", "gedung D", "7-10");

        Search<Object> search = new Search<>();

        while (true) {
            System.out.print("Masukkan keluhan pasien (atau ketik 'selesai' untuk keluar): ");
            String penyakitPasien = scanner.nextLine();

            if (penyakitPasien.equalsIgnoreCase("selesai")) {
                break;
            }

            System.out.print("Masukkan poli dokter: ");
            String poliDokter = scanner.nextLine();

            Object result = search.search(d, penyakitPasien, poliDokter);

            if (result instanceof dokter) {
                dokter searchedDokter = (dokter) result;
                System.out.println("Dokter yang ditemukan: " + searchedDokter.nama() +
                        ", Poli: " + searchedDokter.poli() +
                        ", Ruangan: " + searchedDokter.ruangan() +
                        ", Jam: " + searchedDokter.jam());
            } else if (result instanceof pasien) {
                pasien searchedPasien = (pasien) result;
                System.out.println("Pasien yang ditemukan: " + searchedPasien.nama() +
                        ", Umur: " + searchedPasien.umur() +
                        ", Nik: " + searchedPasien.nik() +
                        ", Keluhan : " + searchedPasien.keluhan());
            } else {
                System.out.println("Tidak ada dokter atau pasien yang sesuai.");
            }
        }

        System.out.println("\nDokter yang bertugas :");
        for (dokter currentDokter : d) {
            System.out.println("nama: " + currentDokter.nama() +
                    ", Poli: " + currentDokter.poli() +
                    ", Ruangan: " + currentDokter.ruangan() +
                    ", Jam: " + currentDokter.jam());
        }

        System.out.println("\n");

        PriorityQueue<pasien> borrowingQueue = new PriorityQueue<>(Comparator.comparing(pasien::kategori));
        borrowingQueue.add(person1);
        borrowingQueue.add(person2);
        borrowingQueue.add(person3);
        borrowingQueue.add(person4);

        LinkedList<pasien> antrianPasien = new LinkedList<>();
        antrianPasien.add(person1);
        antrianPasien.add(person2);
        antrianPasien.add(person3);
        antrianPasien.add(person4);

        System.out.println("Urutan Antrian Berdasarkan Kategori :");
for (pasien pasien : antrianPasien) {
    System.out.println(pasien.nama() + " telah diperiksa dan di berikan resep obat oleh dokter:");

//    Object result = search.search(d, pasien.keluhan(), pasien.kategori());
    
        for (dokter currentDokter : d) {
            if (currentDokter.poli().equalsIgnoreCase(pasien.keluhan())) {
                System.out.println("Nama Dokter: " + currentDokter.nama() +
                        ", Poli Spesialis: " + currentDokter.poli() +
                        ", Ruangan: " + currentDokter.ruangan() +
                        ", Jam: " + currentDokter.jam());
            }
    }

            System.out.println(); // Baris kosong antara setiap pasien
        }

        scanner.close();
    }
}