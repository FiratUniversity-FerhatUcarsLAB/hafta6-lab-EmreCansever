/**
  
 * Ad Soyad: Emre Cansever
 * Ogrenci No: 250541060
 * Tarih: 26.11.2025
**/

   import java.util.Scanner;

public class RestoranSiparis {

    // 1) Ana Yemek Fiyatı
    public static double getMainDishPrice(int secim) {
        switch (secim) {
            case 1: return 85;   // Izgara Tavuk
            case 2: return 120;  // Adana Kebap
            case 3: return 110;  // Levrek
            case 4: return 65;   // Mantı
            default: return 0;
        }
    }

    // 2) Başlangıç Fiyatı
    public static double getAppetizerPrice(int secim) {
        switch (secim) {
            case 1: return 25;   // Çorba
            case 2: return 45;   // Humus
            case 3: return 55;   // Sigara Böreği
            default: return 0;   // 0 → hiç seçilmedi
        }
    }

    // 3) İçecek Fiyatı
    public static double getDrinkPrice(int secim) {
        switch (secim) {
            case 1: return 15;   // Kola
            case 2: return 12;   // Ayran
            case 3: return 35;   // Meyve Suyu
            case 4: return 25;   // Limonata
            default: return 0;
        }
    }

    // 4) Tatlı Fiyatı
    public static double getDessertPrice(int secim) {
        switch (secim) {
            case 1: return 65;   // Künefe
            case 2: return 55;   // Baklava
            case 3: return 35;   // Sütlaç
            default: return 0;
        }
    }

    // 5) Combo Menü mü?
    public static boolean isComboOrder(int ana, int icecek, int tatli) {
        return (ana > 0 && icecek > 0 && tatli > 0);
    }

    // 6) Happy Hour mu?
    public static boolean isHappyHour(int saat) {
        return (saat >= 14 && saat <= 17);
    }

    // 7) İndirim Hesabı
    public static double calculateDiscount(double tutar, boolean combo, boolean ogrenci, int saat, int gun, double icecekFiyat) {
        double indirim = 0;

        // Combo indirimi
        if (combo) {
            indirim += tutar * 0.15;
        }

        // 200 TL üzeri
        if (tutar >= 200) {
            indirim += tutar * 0.10;
        }

        // Happy Hour → içeceklerde %20
        if (isHappyHour(saat) && icecekFiyat > 0) {
            indirim += icecekFiyat * 0.20;
        }

        // Öğrenci indirimi (hafta içi: 1-5)
        if (ogrenci && gun >= 1 && gun <= 5) {
            indirim += (tutar - indirim) * 0.10;
        }

        return indirim;
    }

    // 8) Bahşiş önerisi (%10)
    public static double calculateServiceTip(double tutar) {
        return tutar * 0.10;
    }

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            
            System.out.print("Öğrenci misiniz? (Evet/Hayır): ");
            String ogrenciStr = sc.next();
            boolean ogrenci;
            if (ogrenciStr == "EVET") {
                ogrenci = true;
            } else if (ogrenciStr == "evet") {
                ogrenci = true;
            } else if (ogrenciStr == "Evet") {
                ogrenci = true;
            } else {
                ogrenci = false;
            }

            
            System.out.print("Gün (1=Pzt ... 7=Paz): ");
            int gun = sc.nextInt();
            
            System.out.print("Saat (8-23): ");
            int saat = sc.nextInt();

            
            System.out.print("1-Izgara Tavuk 2-Adana Kebap 3-Levrek 4-Mantı\n");
            System.out.print("Ana Yemek (1-4): ");
            int ana = sc.nextInt();

            System.out.print("1-Çorba 2-Humus 3-Sigara Böreği\n");
            System.out.print("Başlangıç (0-3): ");
            int baslangic = sc.nextInt();

            System.out.print("1-Kola 2-Ayran 3-Taze Meyve Suyu 4-Limonata\n");
            System.out.print("İçecek (0-4): ");
            int icecek = sc.nextInt();

            System.out.print("1-Künefe 2-Baklava 3-Sütlaç");
            System.out.print("Tatlı (0-3): ");
            int tatli = sc.nextInt();

            
            double anaFiyat = getMainDishPrice(ana);
            double baslangicFiyat = getAppetizerPrice(baslangic);
            double icecekFiyat = getDrinkPrice(icecek);
            double tatliFiyat = getDessertPrice(tatli);

            double araToplam = anaFiyat + baslangicFiyat + icecekFiyat + tatliFiyat;
            boolean combo = isComboOrder(ana, icecek, tatli);

            double indirim = calculateDiscount(araToplam, combo, ogrenci, saat, gun, icecekFiyat);
            double toplam = araToplam - indirim;
            double bahsis = calculateServiceTip(toplam);

            
            System.out.println("\n----- SİPARİŞ ÖZETİ -----");
            System.out.printf("Ara Toplam     : %.2f TL%n", araToplam);
            System.out.printf("İndirim        : -%.2f TL%n", indirim);
            System.out.printf("Toplam         : %.2f TL%n", toplam);
            System.out.printf("Bahşiş Önerisi : %.2f TL%n", bahsis);
        }
    }
