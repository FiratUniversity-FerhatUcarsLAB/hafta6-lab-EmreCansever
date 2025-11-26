/**
 * Ad Soyad: Emre Cansever
 * Numara: 250541060
 * Proje: Öğrenci Not Değerlendirme Sistemi
 * Tarih: 26.11.2025
 */

import java.util.Scanner;

public class javaogreniyorum {

    // Sabitler - Değişmeyen değerler
    final static double VIZE_ORANI = 0.30;      // %30
    final static double FINAL_ORANI = 0.40;     // %40
    final static double ODEV_ORANI = 0.30;      // %30
    final static double GECME_NOTU = 50.0;
    final static double ONUR_LISESI_NOTU = 85.0;
    final static double MIN_ONUR_NOT = 70.0;
    final static double BUTUNLEME_MIN = 40.0;
    final static double BUTUNLEME_MAX = 50.0;

    /**
     * METOT 1: Ortalama Hesaplama
     * Parametreler: vize, final, ödev notları
     * Dönüş: Ortalama not (double)
     */
    public static double calculateAverage(double vize, double finalNotu, double odev) {
        double ortalama = (vize * VIZE_ORANI) + (finalNotu * FINAL_ORANI) + (odev * ODEV_ORANI);
        return ortalama;
    }

    /**
     * METOT 2: Geçme Durumu Kontrolü
     * Parametre: ortalama
     * Dönüş: true (geçti) veya false (kaldı)
     */
    public static boolean isPassingGrade(double ortalama) {
        return ortalama >= GECME_NOTU;
    }

    /**
     * METOT 3: Harf Notu Hesaplama
     * Parametre: ortalama
     * Dönüş: Harf notu (String)
     */
    public static String getLetterGrade(double ortalama) {
        if (ortalama >= 90) {
            return "A";
        } else if (ortalama >= 80) {
            return "B";
        } else if (ortalama >= 70) {
            return "C";
        } else if (ortalama >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    /**
     * METOT 4: Onur Listesi Kontrolü
     * Parametreler: ortalama ve tüm notlar
     * Dönüş: true (onur listesinde) veya false (değil)
     * Kural: Ortalama >= 85 VE hiçbir not < 70 olmamalı
     */
    public static boolean isHonorList(double ortalama, double vize, double finalNotu, double odev) {
        // Ortalama 85'ten büyük mü?
        boolean ortalamaYeterli = ortalama >= ONUR_LISESI_NOTU;

        // Tüm notlar 70'ten büyük mü?
        boolean tumNotlarYeterli = (vize >= MIN_ONUR_NOT) &&
                (finalNotu >= MIN_ONUR_NOT) &&
                (odev >= MIN_ONUR_NOT);

        // İkisi de true olmalı
        return ortalamaYeterli && tumNotlarYeterli;
    }

    /**
     * METOT 5: Bütünleme Hakkı Kontrolü
     * Parametre: ortalama
     * Dönüş: true (bütünleme hakkı var) veya false (yok)
     * Kural: 40 <= ortalama < 50
     */
    public static boolean hasRetakeRight(double ortalama) {
        return (ortalama >= BUTUNLEME_MIN) && (ortalama < BUTUNLEME_MAX);
    }

    /**
     * MAIN METOT - Programın başlangıcı
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Kullanıcıdan notları al
        System.out.print("Vize Notu (0-100): ");
        double vize = scanner.nextDouble();

        System.out.print("Final Notu (0-100): ");
        double finalNotu = scanner.nextDouble();

        System.out.print("Ödev Notu (0-100): ");
        double odev = scanner.nextDouble();

        // Metodları kullanarak hesaplamalar yap
        double ortalama = calculateAverage(vize, finalNotu, odev);
        boolean gectiMi = isPassingGrade(ortalama);
        String harfNotu = getLetterGrade(ortalama);
        boolean onurListesi = isHonorList(ortalama, vize, finalNotu, odev);
        boolean butunleme = hasRetakeRight(ortalama);

        // Sonuçları yazdır
        System.out.println("\n=== OGRENCI NOT RAPORU ===");
        System.out.printf("Vize Notu  : %.1f\n", vize);
        System.out.printf("Final Notu : %.1f\n", finalNotu);
        System.out.printf("Odev Notu  : %.1f\n", odev);
        System.out.println("------------------------------");
        System.out.printf("Ortalama   : %.1f\n", ortalama);
        System.out.printf("Harf Notu  : %s\n", harfNotu);

        // Geçme durumu
        if (gectiMi) {
            System.out.println("Durum      : GECTI");
        } else {
            System.out.println("Durum      : KALDI");
        }

        // Onur listesi
        if (onurListesi) {
            System.out.println("Onur Listesi: EVET");
        } else {
            System.out.println("Onur Listesi: HAYIR");
        }

        // Bütünleme
        if (butunleme) {
            System.out.println("Butunleme  : VAR");
        } else {
            System.out.println("Butunleme  : YOK");
        }

        scanner.close();
    }
}
