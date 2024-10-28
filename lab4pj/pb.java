package lab4pj;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class pb {
    // Enum pentru starea echipamentelor
    public enum StareEchipament {
        achizitionat, expus, vandut
    }

    // Enum pentru modul de tipărire
    public enum ModTiparire {
        color, alb_negru
    }

    // Enum pentru formatul de copiere
    public enum FormatCopiere {
        A3, A4
    }

    // Enum pentru sistemele de operare
    public enum SistemOperare {
        windows, linux
    }

    public abstract static class Echipament {
        protected String denumire;
        protected int nrInv;
        protected double pret;
        protected String zonaMag;
        protected StareEchipament stare;
        public String tip;

        public Echipament(String denumire, int nrInv, double pret, String zonaMag, StareEchipament stare, String tip) {
            this.denumire = denumire;
            this.nrInv = nrInv;
            this.pret = pret;
            this.zonaMag = zonaMag;
            this.stare = stare;
            this.tip = tip;
        }

        public abstract void afiseazaDetalii();

        public String getDenumire() {
            return denumire;
        }

        public void modificaStare(StareEchipament stare) {
            this.stare = stare;
        }

        public StareEchipament getStare() {
            return stare;
        }

        public void setStare(StareEchipament stare) {
            this.stare = stare;
        }
    }

    public static class Imprimanta extends Echipament {
        private int ppm; // pagini per minut
        private String dpi; // rezoluție
        private int pCar; // pagini per cartuș
        private ModTiparire modTiparire;

        public Imprimanta(String denumire, int nrInv, double pret, String zonaMag, StareEchipament stare,
                          String tip, int ppm, String dpi, int pCar, ModTiparire modTiparire) {
            super(denumire, nrInv, pret, zonaMag, stare, tip);
            this.ppm = ppm;
            this.dpi = dpi;
            this.pCar = pCar;
            this.modTiparire = modTiparire;
        }

        public void setModTiparire(ModTiparire modTiparire) {
            this.modTiparire = modTiparire;
        }

        @Override
        public void afiseazaDetalii() {
            System.out.println("Imprimanta: " + denumire + ", Nr. Inventar: " + nrInv +
                    ", Preț: " + pret + ", Zona: " + zonaMag + ", Stare: " + stare +
                    ", PPM: " + ppm + ", DPI: " + dpi + ", Pagini/Cartuș: " + pCar +
                    ", Mod Tipărire: " + modTiparire);
        }
    }

    public static class Copiator extends Echipament {
        private int pTon; // pagini per toner
        private FormatCopiere formatCopiere;

        public Copiator(String denumire, int nrInv, double pret, String zonaMag, StareEchipament stare,
                        String tip, int pTon, FormatCopiere formatCopiere) {
            super(denumire, nrInv, pret, zonaMag, stare, tip);
            this.pTon = pTon;
            this.formatCopiere = formatCopiere;
        }

        public void setFormatCopiere(FormatCopiere formatCopiere) {
            this.formatCopiere = formatCopiere;
        }

        @Override
        public void afiseazaDetalii() {
            System.out.println("Copiator: " + denumire + ", Nr. Inventar: " + nrInv +
                    ", Preț: " + pret + ", Zona: " + zonaMag + ", Stare: " + stare +
                    ", Pagini/Toner: " + pTon + ", Format Copiere: " + formatCopiere);
        }
    }

    public static class SistemCalcul extends Echipament {
        private String tipMon; // tipul monitorului
        private double vitProc; // viteza procesorului
        private int cHdd; // capacitatea HDD
        private SistemOperare sistemOperare;

        public SistemCalcul(String denumire, int nrInv, double pret, String zonaMag, StareEchipament stare,
                            String tip, String tipMon, double vitProc, int cHdd, SistemOperare sistemOperare) {
            super(denumire, nrInv, pret, zonaMag, stare, tip);
            this.tipMon = tipMon;
            this.vitProc = vitProc;
            this.cHdd = cHdd;
            this.sistemOperare = sistemOperare;
        }

        public void instaleazaSistemOperare(SistemOperare sistemOperare) {
            this.sistemOperare = sistemOperare;
        }

        @Override
        public void afiseazaDetalii() {
            System.out.println("Sistem de calcul: " + denumire + ", Nr. Inventar: " + nrInv +
                    ", Preț: " + pret + ", Zona: " + zonaMag + ", Stare: " + stare +
                    ", Tip Monitor: " + tipMon + ", Viteză Procesor: " + vitProc +
                    ", Capacitate HDD: " + cHdd + ", Sistem Operare: " + sistemOperare);
        }
    }

    public static void main(String[] args) {
        List<Echipament> echipamente = new ArrayList<>();
        try (Scanner sc = new Scanner(new File("src/lab4pj/echipamente.txt"))) {
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] data = line.split(";");
                String denumire = data[0];
                int nrInv = Integer.parseInt(data[1]);
                double pret = Double.parseDouble(data[2]);
                String zonaMag = data[3];
                StareEchipament stare = StareEchipament.valueOf(data[4].toLowerCase());
                String tip = data[5];

                switch (tip.toLowerCase()) {
                    case "imprimanta":
                        int ppm = Integer.parseInt(data[6]);
                        String dpi = data[7];
                        int pCar = Integer.parseInt(data[8]);
                        ModTiparire modTiparire = ModTiparire.valueOf(data[9].toLowerCase());
                        echipamente.add(new Imprimanta(denumire, nrInv, pret, zonaMag, stare, tip, ppm, dpi, pCar, modTiparire));
                        break;
                    case "copiator":
                        int pTon = Integer.parseInt(data[6]);
                        FormatCopiere formatCopiere = FormatCopiere.valueOf(data[7].toUpperCase());
                        echipamente.add(new Copiator(denumire, nrInv, pret, zonaMag, stare, tip, pTon, formatCopiere));
                        break;
                    case "sistem de calcul":
                        String tipMon = data[6];
                        double vitProc = Double.parseDouble(data[7]);
                        int cHdd = Integer.parseInt(data[8]);
                        SistemOperare sistemOperare = SistemOperare.valueOf(data[9].toLowerCase());
                        echipamente.add(new SistemCalcul(denumire, nrInv, pret, zonaMag, stare, tip, tipMon, vitProc, cHdd, sistemOperare));
                        break;
                    default:
                        System.out.println("Tip de echipament necunoscut: " + tip);
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fisierul nu a fost gasit!");
            exit(1);
        } catch (Exception e) {
            System.out.println("Eroare la citirea fișierului: " + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        // Interfața de utilizator cu opțiuni pentru utilizator
        String denumire_c;
        System.out.println("\nMeniu:");
        System.out.println("1. Afișarea tuturor echipamentelor");
        System.out.println("2. Afișarea imprimantelor");
        System.out.println("3. Afișarea copiatoarelor");
        System.out.println("4. Afișarea sistemelor de calcul");
        System.out.println("5. Modificarea stării unui echipament");
        System.out.println("6. Setarea modului de tipărire pentru imprimante");
        System.out.println("7. Setarea formatului de copiere pentru copiatoare");
        System.out.println("8. Instalarea sistemului de operare pentru sisteme de calcul");
        System.out.println("9. Afișarea echipamentelor vândute");
        System.out.println("0. Ieșire");

        while (true) {
            System.out.print("Introduceti optiunea dorita: ");
            int opt = scanner.nextInt();
            scanner.nextLine(); // Consumă linia rămasă după nextInt

            switch (opt) {
                case 0 :
                    exit(0);
                    break;
                case 1 :
                    echipamente.forEach(Echipament::afiseazaDetalii);
                break;
                case 2 :
                    echipamente.stream().filter(e -> e.tip.equals("imprimanta")).forEach(Echipament::afiseazaDetalii);
                break;
                case 3 :
                    echipamente.stream().filter(e -> e.tip.equals("copiator")).forEach(Echipament::afiseazaDetalii);
                    break;
                case 4 :
                    echipamente.stream().filter(e -> e.tip.equals("sistem de calcul")).forEach(Echipament::afiseazaDetalii);
                    break;
                case 5 :
                {
                    System.out.print("Introduceti denumirea echipamentului: ");
                    denumire_c = scanner.nextLine();
                    System.out.print("Introduceti noua stare : ");
                    try {
                        StareEchipament stare_c = StareEchipament.valueOf(scanner.nextLine().toLowerCase());
                        String finalDenumire_c = denumire_c;
                        echipamente.stream()
                                .filter(e -> e.getDenumire().equals(finalDenumire_c))
                                .forEach(e -> e.setStare(stare_c));
                    } catch (IllegalArgumentException e) {
                        System.out.println("Stare necunoscuta!");
                    }
                }
                case 6 : {
                    System.out.print("Introduceti denumirea imprimantei: ");
                    denumire_c = scanner.nextLine();
                    System.out.print("Introduceti mod scriere : ");
                    try {
                        ModTiparire modTiparire = ModTiparire.valueOf(scanner.nextLine().toLowerCase());
                        String finalDenumire_c1 = denumire_c;
                        echipamente.stream()
                                .filter(e -> e.getDenumire().equals(finalDenumire_c1) && e.tip.equals("imprimanta"))
                                .forEach(e -> ((Imprimanta) e).setModTiparire(modTiparire));
                    } catch (IllegalArgumentException e) {
                        System.out.println("Mod de tiparire necunoscut!");
                    }
                }
                break;
                case 7 :
                {
                    System.out.print("Introduceti denumirea copiatorului: ");
                    denumire_c = scanner.nextLine();
                    System.out.print("Introduceti formatul de copiere (A3/A4): ");
                    try {
                        FormatCopiere formatCopiere = FormatCopiere.valueOf(scanner.nextLine().toUpperCase());
                        String finalDenumire_c2 = denumire_c;
                        echipamente.stream()
                                .filter(e -> e.getDenumire().equals(finalDenumire_c2) && e.tip.equals("copiator"))
                                .forEach(e -> ((Copiator) e).setFormatCopiere(formatCopiere));
                    } catch (IllegalArgumentException e) {
                        System.out.println("Format de copiere necunoscut!");
                    }
                }
                break;
                case 8 :
                {
                    System.out.print("Introduceti denumirea sistemului de calcul: ");
                    denumire_c = scanner.nextLine();
                    System.out.print("Introduceti sistemul de operare (windows/linux): ");
                    try {
                        SistemOperare sistemOperare = SistemOperare.valueOf(scanner.nextLine().toLowerCase());
                        String finalDenumire_c3 = denumire_c;
                        echipamente.stream()
                                .filter(e -> e.getDenumire().equals(finalDenumire_c3) && e.tip.equals("sistem de calcul"))
                                .forEach(e -> ((SistemCalcul) e).instaleazaSistemOperare(sistemOperare));
                    } catch (IllegalArgumentException e) {
                        System.out.println("Sistem de operare necunoscut!");
                    }
                }
                break;
                case 9 :
                    echipamente.stream().filter(e -> e.getStare() == StareEchipament.vandut).forEach(Echipament::afiseazaDetalii);
                    break;
                default :System.out.println("Optiunea invalida!");
                break;
            }
        }
    }
}
