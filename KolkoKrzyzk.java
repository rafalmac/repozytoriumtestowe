/*
 7. Gra „kółko i krzyżyk” dla dwóch osób. Plansza gry o wymiarze 3x3 realizowana 
za pomocą przycisków.
Program zrealizowałem z wykorzystaniem Swinga. 
 */
package KK;

import com.sun.glass.events.WindowEvent;
import com.sun.glass.ui.Window;
import java.awt.Color;          
import javax.swing.JFrame;
import javax.swing.JOptionPane; //import klasy JOptionPane z biblioteki swing

/**
 *
 * @author rafalmaciejowski
 */
public class KolkoKrzyzk extends javax.swing.JFrame { //klasa gry

    private String kogokolej= "X";//stworzenie i przypisanie referencji klasy string dla znaku X
    private String graczpierwszy = "Gracz pierwszy";//tworzymy stringa z graczem pierwszym
            String graczdrugi = "Gracz drugi";//analogicznie stringa z graczem drugim
            int wynikpierwszegogracza = 0;//zmienna int do ktorej bedziemy dodawać wynik
            int wynikdrugiegogracza = 0;//zmienna w ktorej beda wyniki drugiego gracza
    private String ruchgracza; //string narazie pusty, ale bedzie wpisywany tu kto akurat sie rusza
            String wyjsciezgry;
           
    
    public KolkoKrzyzk() { //właściwości okna aplikacji
        initComponents();//metoda swinga inicjalizujaca komponenty(JButton, JLabel itd)
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);//nadpisanie metody ktora definiuje jak ma być zamykane okno
        boolean jestOk = nazwagracza();//maja sie wyswietlic okna ktore pobiora nazwy graczy
        if (!jestOk)
            System.exit(0);
        wynikgry();//wyswietlenie wyniku gracza
                     
    }
    
    
    @Override
    public void dispose()//analogiczne do funkcji wyjściezgry 
                        //funkcja nadpisująca tą metode setDefaultCloseOperation
                        //po kliknięciu X zamiast wyjścia z programu najpierw wyskoczy okno
                        //które zapyta czy napewno chcemy wyjść z programu
    {
        int taknie = JOptionPane.showConfirmDialog(null,
                "Czy napewno chcesz opuścic grę?",
                "Zakończenie gry.",
                JOptionPane.YES_OPTION,//stworzenie panelu z przyciskiem yes/no
                JOptionPane.CLOSED_OPTION);
        
        if (taknie==JOptionPane.YES_OPTION)
        {
            super.dispose();//wywołanie metody dispose
            System.exit(0);//zakonczenie programu
        }
    }
    
    
    private void wynikgry()//wpisanie do panelu dolnego jLabel1 wyniku dla danego gracza
    {
        
        if(kogokolej.equals("X"))//warunek ze jak X rowne kogokolej to ruch nalezy do gracza pierwszego
            {
             ruchgracza = graczpierwszy;
            }
        else// w przypadku gdy kogokolej = O to ruch wykonuje gracz drugi
            {
             ruchgracza = graczdrugi;   
            }
       
        //ustawienie tekstu wyswietlanego w panelu jLabel1
        //ma wyswietlic tekst potem pobrana nazwe gracza i wynik wykorzystujac metode string.valueof(int)
        //która zwraca wartość int zmiennej wynikpierwszego gracza
       jLabel1.setText("wynik " + graczpierwszy + "= "  + String.valueOf(wynikpierwszegogracza) +
               "      " + "Twoj ruch: " + ruchgracza + " "
               + "           "+ "wynik " + graczdrugi + "= " + String.valueOf(wynikdrugiegogracza) );
       
    }
    

    private void wyjsciezgry()//stworzenie okna ktore w przypdku kliniecia wyjscie bedzie wymagalo potwierdzenia
    {
        int taknie = JOptionPane.showConfirmDialog(null,
                "Czy napewno chcesz opuścic grę?",
                "Zakończenie gry.",
                JOptionPane.YES_OPTION,//stworzenie panelu z przyciskiem yes/no
                JOptionPane.CLOSED_OPTION);//nie moglem wpaść na to w jaki sposób sprawić aby okno się zamykało po kliknieciu na No
                                            //ale to zamkniecie okna sprawiało że można kontyuować program dalej
                                            // odkryłem po stu probach ,że funkcja closed_option działa
                                            
        //postanowiłem użyć instrukcji switch bo pasuje w momencie gdy trzeba podjąc kilka decyzji i w zależności 
        // na którą etykiete klikniemy wykonuje sie dana instrukcja po czym następuje przerwanie
        switch(taknie)
        {
            case JOptionPane.YES_OPTION: System.exit(0);//po kliknieciu YES gra sie wyłącza
            break;
            case JOptionPane.CLOSED_OPTION://po kliknieciu NO jedynie zamyka się okienko pytające o wyjscie z gry
            break;
         
        }
        
    }
          
            
            
    private boolean nazwagracza()//chce pobrać imiona zawodnikow
    {
        boolean jestOk = true;
        
      graczpierwszy = JOptionPane.showInputDialog(this,//wykorzystanie funkcji showInputDialog, ktora pobiera 
              "Nazwa pierwszego gracza:",              //wyswietlany komunikat
              "Wybór nazwy gracza!",              //tytuł okna
              JOptionPane.INFORMATION_MESSAGE);        //typ okna ze informacyjne
     
      if (graczpierwszy==null)
          jestOk=false;
     
      if (jestOk)//jesli w okienku które pobiera nazwe zawodników klikniemy ok to z pierwszego gracza przechodzimy do drugiego
      {
        graczdrugi = JOptionPane.showInputDialog(this,
                 "Nazwa drugiego gracza:",
                 "Wybór nazwy gracza!",
                 JOptionPane.INFORMATION_MESSAGE);

        if (graczdrugi==null)
          jestOk=false;//jezeli klikniemy cancel to zakonczymy program(warunek w glownym oknie KolkoKrzyzyk
        
        if (jestOk)
        {
            //tworzenie struktury ktora uniemozliwi wpisanie takich samych nazw graczy
            if(graczpierwszy.equalsIgnoreCase(graczdrugi))//warunek ktory porownuje bez wzgledu na wielkosc liter
            {
               JOptionPane.showMessageDialog(this,//pokaze sie bład informujacy o tym aby zmienic nazwe
                       "Nazwy graczy nie mogą być takie same!"
                               + "\n             Zmień nazwe!",
                       "Nieprawidłowa nazwa gracza",
                       JOptionPane.ERROR_MESSAGE);
               nazwagracza();//po wyjsciu z bledu ponownie bedzie trzeba wybrac nazwe graczy
            }
        }
      }
     return(jestOk);
    }
    
        
    private void Xwygrywa()//okno informujące że gracz pierwszy wygrywa
    {
        JOptionPane.showMessageDialog(this,
                graczpierwszy + " wygrywa!",
                "Wygrana!",
                JOptionPane.INFORMATION_MESSAGE);
        wynikpierwszegogracza++;//po tym jak wyskoczy okno ze wygral X, ma byc to doliczone do int pierwszego gracza
        resetgry();//po zamknięciu okna gra sie resetuje
        
    }
    
    
    private void Owygrywa()//okno informujące że gracz drugi wygrywa
    {
        JOptionPane.showMessageDialog(this,//nazwa okna w ktorym wyswietlane
                graczdrugi + " wygrywa!",//wyswietlany komunikat
                "Wygrana!",//tytuł okienka
                JOptionPane.INFORMATION_MESSAGE);//typ okna 
        wynikdrugiegogracza++;
        resetgry();//po zamknieciu okna gra restartuje sie
        
    }
    
    
    private void remisgry()/*zdefiniowanie kiedy zachodzi remis oraz dodanie
                            okna które informuje o remisie*/ 
    {
        //zdefiniowanie przycisków 
    String jeden = jButton1.getText();
    String dwa =   jButton2.getText();
    String trzy =  jButton3.getText();
    String cztery =jButton4.getText();
    String piec =  jButton5.getText();
    String szesc = jButton6.getText();
    String siedem =jButton7.getText();
    String osiem = jButton8.getText();
    String dziewiec = jButton9.getText();
    
       /*warunek, z wykorzystaniem ilorazu logicznego, muszą być
        //takie same znaki aby była prawda*/
    if(jeden != "" && dwa != "" && trzy != "" && cztery != "" &&
       piec != "" && szesc != "" && siedem != "" && osiem != "" &&
            dziewiec != "")
    {
     JOptionPane.showMessageDialog(this,//okno informujące o spełneniu warunku-remis
             "Gra zakonczona remisem",
             "Remis",
             JOptionPane.INFORMATION_MESSAGE);
     resetgry();//po zamknieciu okna nastapi reset gry
    
    }
    }
    
    
    private void okresleniekogokolej()//warunek okreslający kto następny
    {
        if(kogokolej.equals("X"))//warunek logiczny porównuje string kogokolej do tego co w znaku X
                                   // jezeli są sobie równe to wykonujemy instrukcje
        {
            kogokolej = "O";//istrukcja nadpisujaca kogokolej obiektem O
        }
        else//w przeciwnym przypadku gdy warunek logiczny niespełniony wykonujemy instrukcje drugą
        {
            kogokolej = "X";//referencja kogokolej dla obiektu X
        }
                
    
    }
    
      
    private void resetgry()//rozpoczęcie gry od nowa
    {
        jButton1.setText("");//wyczyszczenie wszystkich buttonów
        jButton2.setText("");//poprzez ustawienie tekstu na pusty
        jButton3.setText("");
        jButton4.setText("");
        jButton5.setText("");
        jButton6.setText("");
        jButton7.setText("");
        jButton8.setText("");
        jButton9.setText("");
        wynikgry();
    }
    
        
    private void ktowygrywa()//warunek ktory gracz wygrywa i dlaczego
    {
    String jeden = jButton1.getText();//definiowanie przycisków
    String dwa =   jButton2.getText();
    String trzy =  jButton3.getText();
    String cztery =jButton4.getText();
    String piec =  jButton5.getText();
    String szesc = jButton6.getText();
    String siedem =jButton7.getText();
    String osiem = jButton8.getText();
    String dziewiec = jButton9.getText();
    
    
    //warunek na takie ustawienie X na przyciskach aby X wygralo
    if(jeden == "X" && dwa == "X" && trzy == "X" )//warunek logiczny porównujący klase stringa do znaku "X"
    {                                              //z użyciem koniunkcji tak aby trzy X daly wygraną
       Xwygrywa();
    }
    if(cztery == "X" && piec == "X" && szesc == "X" )
    {
       Xwygrywa();
    }
    if(siedem == "X" && osiem == "X" && dziewiec == "X" )
    {
       Xwygrywa();
    }
    if(jeden == "X" && piec == "X" && dziewiec == "X" )
    {
       Xwygrywa();
    }
    if(trzy == "X" && piec == "X" && siedem == "X" )
    {
       Xwygrywa();
    }
    if(jeden == "X" && cztery == "X" && siedem == "X" )
    {
       Xwygrywa();
    }
    if(dwa == "X" && piec == "X" && osiem == "X" )
    {
       Xwygrywa();
    }
    if(trzy == "X" && szesc == "X" && dziewiec == "X" )
    {
       Xwygrywa();
    }
    
    
    // warunki dla ktorych O wygrywa
     if(jeden == "O" && dwa == "O" && trzy == "O" )
    {
       Owygrywa();
    }
    if(cztery == "O" && piec == "O" && szesc == "O" )
    {
       Owygrywa();
    }
    if(siedem == "O" && osiem == "O" && dziewiec == "O" )
    {
       Owygrywa();
    }
    if(jeden == "O" && piec == "O" && dziewiec == "O" )
    {
       Owygrywa();
    }
    if(trzy == "O" && piec == "O" && siedem == "O" )
    {
       Owygrywa();
    }
    if(jeden == "O" && cztery == "O" && siedem == "O" )
    {
       Owygrywa();
    }
    if(dwa == "O" && piec == "O" && osiem == "O" )
    {
       Owygrywa();
    }
    if(trzy == "O" && szesc == "O" && dziewiec == "O" )
    {
       Owygrywa();
    }
    if(jeden == "O" && dwa == "O" && trzy == "O" )
    {
       Owygrywa();
    }
    }
    
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel_GridHolder = new javax.swing.JPanel();
        jPanel_1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel_2 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jPanel_3 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jPanel_4 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jPanel_5 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jPanel_6 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jPanel_7 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jPanel_8 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jPanel_9 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Kółko i Krzyżyk");
        setPreferredSize(new java.awt.Dimension(600, 600));

        jPanel1.setBackground(new java.awt.Color(189, 189, 189));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("W");
        jLabel1.setToolTipText("");
        jLabel1.setMaximumSize(new java.awt.Dimension(75, 40));
        jPanel1.add(jLabel1, java.awt.BorderLayout.PAGE_END);

        jPanel_GridHolder.setBackground(new java.awt.Color(153, 153, 153));
        jPanel_GridHolder.setLayout(new java.awt.GridLayout(3, 3, 1, 1));

        jPanel_1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel_1.setLayout(new java.awt.BorderLayout());

        jButton1.setFont(new java.awt.Font("STIXVariants", 1, 98)); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel_1.add(jButton1, java.awt.BorderLayout.CENTER);

        jPanel_GridHolder.add(jPanel_1);

        jPanel_2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel_2.setLayout(new java.awt.BorderLayout());

        jButton2.setFont(new java.awt.Font("STIXVariants", 1, 98)); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel_2.add(jButton2, java.awt.BorderLayout.CENTER);

        jPanel_GridHolder.add(jPanel_2);

        jPanel_3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel_3.setLayout(new java.awt.BorderLayout());

        jButton3.setFont(new java.awt.Font("STIXVariants", 1, 98)); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel_3.add(jButton3, java.awt.BorderLayout.CENTER);

        jPanel_GridHolder.add(jPanel_3);

        jPanel_4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel_4.setLayout(new java.awt.BorderLayout());

        jButton4.setFont(new java.awt.Font("STIXVariants", 1, 98)); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel_4.add(jButton4, java.awt.BorderLayout.CENTER);

        jPanel_GridHolder.add(jPanel_4);

        jPanel_5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel_5.setLayout(new java.awt.BorderLayout());

        jButton5.setFont(new java.awt.Font("STIXVariants", 1, 98)); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel_5.add(jButton5, java.awt.BorderLayout.CENTER);

        jPanel_GridHolder.add(jPanel_5);

        jPanel_6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel_6.setLayout(new java.awt.BorderLayout());

        jButton6.setFont(new java.awt.Font("STIXVariants", 1, 98)); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel_6.add(jButton6, java.awt.BorderLayout.CENTER);

        jPanel_GridHolder.add(jPanel_6);

        jPanel_7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel_7.setLayout(new java.awt.BorderLayout());

        jButton7.setFont(new java.awt.Font("STIXVariants", 1, 98)); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel_7.add(jButton7, java.awt.BorderLayout.CENTER);

        jPanel_GridHolder.add(jPanel_7);

        jPanel_8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel_8.setLayout(new java.awt.BorderLayout());

        jButton8.setFont(new java.awt.Font("STIXVariants", 1, 98)); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel_8.add(jButton8, java.awt.BorderLayout.CENTER);

        jPanel_GridHolder.add(jPanel_8);

        jPanel_9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel_9.setLayout(new java.awt.BorderLayout());

        jButton9.setFont(new java.awt.Font("STIXVariants", 1, 98)); // NOI18N
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel_9.add(jButton9, java.awt.BorderLayout.CENTER);

        jPanel_GridHolder.add(jPanel_9);

        jPanel1.add(jPanel_GridHolder, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jMenu1.setText("Opcje");

        jMenuItem1.setText("Nowa gra");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Informacje");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Zakończ gre");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /*przypisanie przyciskom akcji tz. okreslenie co sie dzieje gdy wcisne przycisk
    tz gdy X podswietlenie zielone gdy O podswietlenie magenta,
     na koniec warunki: ze po X zawsze O; weryfikacja kto wygrał w zaleznosci od kombinacji X i O;
    i sprawdzenie czy czasem nie ma remisu */
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jButton2.setText(kogokolej);//po wcisnieciu klawisza ustawiamy znak ze stringa
        if(kogokolej.equals("X"))//porównuje znak z stringa kogokolej z X 
        {
            jButton2.setForeground(Color.GREEN);//jesli sie zgadza że jest tam X to wyswietla się na zielono X
        }
        else
        {
            jButton2.setForeground(Color.MAGENTA);//jesli sie nie zgadza to wyswietla sie na magenta O
        }
        okresleniekogokolej();//okreslamy kogo kolej nastepnie
        wynikgry();//w zależnosci ktory gracz wykonuje ruch zostaje to wypisane w jLabel
        ktowygrywa();//kto wygral
        remisgry();// sprawdzamy czy aby nie ma remisu gry
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        jButton6.setText(kogokolej);
        if(kogokolej.equals("X"))
        {
            jButton6.setForeground(Color.GREEN);
        }
        else
        {
            jButton6.setForeground(Color.MAGENTA);
        }
        okresleniekogokolej();
        wynikgry();
        ktowygrywa();
        remisgry();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jButton1.setText(kogokolej);
        if(kogokolej.equals("X"))
        {
            jButton1.setForeground(Color.GREEN);
        }
        else
        {
            jButton1.setForeground(Color.MAGENTA);
        }
        okresleniekogokolej();
        wynikgry();
        ktowygrywa();
        remisgry();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        jButton3.setText(kogokolej);
        if(kogokolej.equals("X"))
        {
            jButton3.setForeground(Color.GREEN);
        }
        else
        {
            jButton3.setForeground(Color.MAGENTA);
        }
        okresleniekogokolej();
        wynikgry();
        ktowygrywa();
        remisgry();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        jButton4.setText(kogokolej);
        if(kogokolej.equals("X"))
        {
            jButton4.setForeground(Color.GREEN);
        }
        else
        {
            jButton4.setForeground(Color.MAGENTA);
        }
        okresleniekogokolej();
        wynikgry();
        ktowygrywa();
        remisgry();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        jButton5.setText(kogokolej);
        if(kogokolej.equals("X"))
        {
            jButton5.setForeground(Color.GREEN);
        }
        else
        {
            jButton5.setForeground(Color.MAGENTA);
        }
        okresleniekogokolej();
        wynikgry();
        ktowygrywa();
        remisgry();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        jButton7.setText(kogokolej);
        if(kogokolej.equals("X"))
        {
            jButton7.setForeground(Color.GREEN);
        }
        else
        {
            jButton7.setForeground(Color.MAGENTA);
        }
        okresleniekogokolej();
        wynikgry();
        ktowygrywa();
        remisgry();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        jButton8.setText(kogokolej);
        if(kogokolej.equals("X"))
        {
            jButton8.setForeground(Color.GREEN);
        }
        else
        {
            jButton8.setForeground(Color.MAGENTA);
        }
        okresleniekogokolej();
        wynikgry();
        ktowygrywa();
        remisgry();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        jButton9.setText(kogokolej);
        if(kogokolej.equals("X"))
        {
            jButton9.setForeground(Color.GREEN);
        }
        else
        {
            jButton9.setForeground(Color.MAGENTA);
        }
        okresleniekogokolej();
        wynikgry();
        ktowygrywa();
        remisgry();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        jButton1.setText("");//wyczyszczenie wszystkich buttonów
        jButton2.setText("");//poprzez ustawienie tekstu na pusty
        jButton3.setText("");
        jButton4.setText("");
        jButton5.setText("");
        jButton6.setText("");
        jButton7.setText("");
        jButton8.setText("");
        jButton9.setText("");
        wynikpierwszegogracza = 0;//zerowanie wynikow zmiennych
        wynikdrugiegogracza = 0;
        nazwagracza();//ponowne wpisanie nazw graczy w nowej grze
        wynikgry();
        
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
       wyjsciezgry();
               
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        JOptionPane.showMessageDialog(null,
             "Autor: Rafał Maciejowski"
                     + "\nWykonano: 11.01.2017"
                     + "\nProjekt w ramach przedmiotu AMRM",
             "Informacje",
             JOptionPane.INFORMATION_MESSAGE);
                
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(KolkoKrzyzk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KolkoKrzyzk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KolkoKrzyzk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KolkoKrzyzk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KolkoKrzyzk().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel_1;
    private javax.swing.JPanel jPanel_2;
    private javax.swing.JPanel jPanel_3;
    private javax.swing.JPanel jPanel_4;
    private javax.swing.JPanel jPanel_5;
    private javax.swing.JPanel jPanel_6;
    private javax.swing.JPanel jPanel_7;
    private javax.swing.JPanel jPanel_8;
    private javax.swing.JPanel jPanel_9;
    private javax.swing.JPanel jPanel_GridHolder;
    // End of variables declaration//GEN-END:variables

    private void getPlayerNames() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
