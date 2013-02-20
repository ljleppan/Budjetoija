package budjetoija.kayttoliittyma;

import budjetoija.logiikka.Paivamaara;
import budjetoija.logiikka.Summa;
import budjetoija.logiikka.Tili;
import budjetoija.logiikka.ToistuvaTilitapahtuma;
import budjetoija.logiikka.YksittainenTilitapahtuma;
import budjetoija.util.TallentajaLataaja;
import java.awt.Font;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;

/**
 * Budjetoijan käyttöliittymä.
 */
public class GUI extends javax.swing.JFrame implements Runnable {
    
    /** Suurin käyttöliittymän sallima tekstuaalisen kuvauksen pituus. */
    public static final int SUURIN_KUVAUKSEN_PITUUS = 35;
    
    /** Suurin käyttöliittymän sallima numeraalin pituus. */
    public static final int SUURIN_NUMERAALIN_PITUUS = 8;
    
    /** Suurin käyttöliittymän sallima tilin nimi. */
    public static int SUURIN_TILIN_NIMI = 20;

    /** Tilin tallentamisesta ja lataamisesta vastaavan luokan ilmentymä. */
    TallentajaLataaja io;
    
    /** Käsiteltävä tili. */
    Tili tili;
    
    /** Näkyvän aikavälin alkupäivämäärä. */
    Paivamaara alkuPvm;
    
    /** Näkyvän aikavälin loppupäivämäärä. */
    Paivamaara loppuPvm;

    /**
     * Luokan konstruktori.
     * @param tili Käsiteltävä tili.
     * @param tallentajalataaja Tilin tallentamisesta ja lataamisesta vastaava TallentajaLataaja-luokan ilmentymä.
     * @see Tili
     * @see TallentajaLataaja
     */
    public GUI(Tili tili, TallentajaLataaja tallentajalataaja) {
        this.tili = tili;
        this.io = tallentajalataaja;

        this.alkuPvm = new Paivamaara();
        this.alkuPvm.set(Calendar.DAY_OF_MONTH, 1);
        alkuPvm.clear(Calendar.HOUR);
        alkuPvm.clear(Calendar.MINUTE);
        alkuPvm.clear(Calendar.SECOND);
        alkuPvm.clear(Calendar.MILLISECOND);

        this.loppuPvm = new Paivamaara();
        this.loppuPvm.set(Calendar.DAY_OF_MONTH, this.loppuPvm.getActualMaximum(Calendar.DAY_OF_MONTH));
        loppuPvm.clear(Calendar.HOUR);
        loppuPvm.clear(Calendar.MINUTE);
        loppuPvm.clear(Calendar.SECOND);
        loppuPvm.clear(Calendar.MILLISECOND);

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
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        initComponents();

    }

    /**
     * Käynnistää käyttöliittymän.
     */
    @Override
    public void run() {
        this.setVisible(true);
        paivitaListaus();
        paivitaOtsikko();
    }

    /**
     * Käyttöliittymän rakentava metodi.
     * Swingbuilderin generoimaa koodia, jota ei tule missään tilanteessa
     * muuntaa käsin. Muutamiseen käytettävä Swingbuilderia.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        paaPaneeli = new javax.swing.JPanel();
        listausScrollPane = new javax.swing.JScrollPane();
        Paivamaara alku = (Paivamaara)alkuPvm.clone();
        Paivamaara loppu = (Paivamaara)loppuPvm.clone();
        ArrayList<Object> tapahtumat = new ArrayList();
        tapahtumat.addAll(tili.getToistuvatTilitapahtumatAjalta(alku, loppu));
        tapahtumat.add("--");
        tapahtumat.addAll(tili.getTilitapahtumatAjalta(alku, loppu));
        listausList = new javax.swing.JList(tapahtumat.toArray());
        ylaPaneeli = new javax.swing.JPanel();
        hallitseTilitapahtumiaLabel = new javax.swing.JLabel();
        lisaaTilitapahtumaButton = new javax.swing.JButton();
        muunnaTilitapahtumaButton = new javax.swing.JButton();
        poistaTilitapahtumaButton = new javax.swing.JButton();
        nakymaLabel = new javax.swing.JLabel();
        nakymaAlkuKuukausiSpinner = new javax.swing.JSpinner();
        nakymaAlkuVuosiSpinner = new javax.swing.JSpinner();
        nakymaAlkuPaivaSpinner = new javax.swing.JSpinner();
        nakymaLoppuPaivaSpinner = new javax.swing.JSpinner();
        nakymaLoppuKuukausiSpinner = new javax.swing.JSpinner();
        nakymaLoppuVuosiSpinner = new javax.swing.JSpinner();
        meneButton = new javax.swing.JButton();
        Logo = new javax.swing.JLabel();
        sivuPalkki = new javax.swing.JPanel();
        kuvausLabel = new javax.swing.JLabel();
        kuvausTextField = new javax.swing.JTextField();
        summaLabel = new javax.swing.JLabel();
        summaTextField = new javax.swing.JTextField();
        paivaLabel = new javax.swing.JLabel();
        paivaTextField = new javax.swing.JTextField();
        kuukausiLabel = new javax.swing.JLabel();
        kuukausiTextField = new javax.swing.JTextField();
        vuosiLabel = new javax.swing.JLabel();
        vuosiTextField = new javax.swing.JTextField();
        tallennaButton = new javax.swing.JButton();
        toistuvaAlkuPaivaLabel = new javax.swing.JLabel();
        toistuvaAlkuPaivaTextField = new javax.swing.JTextField();
        toistuvaAlkuKuukausiLabel = new javax.swing.JLabel();
        toistuvaAlkuKuukausiTextField = new javax.swing.JTextField();
        toistuvaAlkuVuosiTextField = new javax.swing.JTextField();
        toistuvaAlkuVuosiLabel = new javax.swing.JLabel();
        toistuvuusCheckBox = new javax.swing.JCheckBox();
        toistuvaLoppuKuukausiLabel = new javax.swing.JLabel();
        toistuvaLoppuKuukausiTextField = new javax.swing.JTextField();
        toistuvaLoppuPaivaLabel = new javax.swing.JLabel();
        toistuvaLoppuPaivaTextField = new javax.swing.JTextField();
        toistuvaLoppuVuosiTextField = new javax.swing.JTextField();
        toistuvaLoppuVuosiLabel = new javax.swing.JLabel();
        yhteenvetoPane = new javax.swing.JScrollPane();
        yhteenvetoTextArea = new javax.swing.JTextArea();
        menuBar = new javax.swing.JMenuBar();
        tiliMenu = new javax.swing.JMenu();
        uusiMenuItem = new javax.swing.JMenuItem();
        avaaMenuItem = new javax.swing.JMenuItem();
        tallennaMenuItem = new javax.swing.JMenuItem();
        menuSeparator1 = new javax.swing.JPopupMenu.Separator();
        vaihdaTilinNimiMenuItem = new javax.swing.JMenuItem();
        menuSeparator2 = new javax.swing.JPopupMenu.Separator();
        poistuMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Buddy - pientalouden budjetoija");
        setPreferredSize(null);
        setResizable(false);

        listausList.setFont(new java.awt.Font("Monospaced", 0, 15)); // NOI18N
        listausList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listausList.setFont(new Font("Monospaced", Font.PLAIN, 12));
        listausList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listausListValueChanged(evt);
            }
        });
        listausScrollPane.setViewportView(listausList);

        hallitseTilitapahtumiaLabel.setText("Tilitapahtuma");

        lisaaTilitapahtumaButton.setText("Uusi");
        lisaaTilitapahtumaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lisaaTilitapahtumaButtonActionPerformed(evt);
            }
        });

        muunnaTilitapahtumaButton.setText("Muunna");
        muunnaTilitapahtumaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                muunnaTilitapahtumaButtonActionPerformed(evt);
            }
        });

        poistaTilitapahtumaButton.setText("Poista");
        poistaTilitapahtumaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                poistaTilitapahtumaButtonActionPerformed(evt);
            }
        });

        nakymaLabel.setText("Aikaväli");

        nakymaAlkuKuukausiSpinner.setModel(new javax.swing.SpinnerNumberModel((this.alkuPvm.get(Calendar.MONTH) +1), 1, 12, 1));

        nakymaAlkuVuosiSpinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(this.alkuPvm.get(Calendar.YEAR)), Integer.valueOf(0), null, Integer.valueOf(1)));

        nakymaAlkuPaivaSpinner.setModel(new javax.swing.SpinnerNumberModel(1, 1, 31, 1));

        nakymaLoppuPaivaSpinner.setModel(new javax.swing.SpinnerNumberModel(this.loppuPvm.getActualMaximum(Calendar.DAY_OF_MONTH), 1, 31, 1));

        nakymaLoppuKuukausiSpinner.setModel(new javax.swing.SpinnerNumberModel((this.loppuPvm.get(Calendar.MONTH) + 1), 1, 12, 1));

        nakymaLoppuVuosiSpinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(this.loppuPvm.get(Calendar.YEAR)), Integer.valueOf(0), null, Integer.valueOf(1)));

        meneButton.setText("Mene");
        meneButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                meneButtonActionPerformed(evt);
            }
        });

        Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/buddy copy.jpg"))); // NOI18N
        Logo.setRequestFocusEnabled(false);

        nakymaAlkuVuosiSpinner.setEditor(new JSpinner.NumberEditor(nakymaAlkuVuosiSpinner, "#"));
        nakymaLoppuVuosiSpinner.setEditor(new JSpinner.NumberEditor(nakymaLoppuVuosiSpinner, "#"));

        javax.swing.GroupLayout ylaPaneeliLayout = new javax.swing.GroupLayout(ylaPaneeli);
        ylaPaneeli.setLayout(ylaPaneeliLayout);
        ylaPaneeliLayout.setHorizontalGroup(
            ylaPaneeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ylaPaneeliLayout.createSequentialGroup()
                .addGroup(ylaPaneeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nakymaLabel)
                    .addGroup(ylaPaneeliLayout.createSequentialGroup()
                        .addGroup(ylaPaneeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nakymaAlkuPaivaSpinner, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nakymaLoppuPaivaSpinner, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ylaPaneeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ylaPaneeliLayout.createSequentialGroup()
                                .addComponent(nakymaAlkuKuukausiSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nakymaAlkuVuosiSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ylaPaneeliLayout.createSequentialGroup()
                                .addComponent(nakymaLoppuKuukausiSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nakymaLoppuVuosiSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(meneButton, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(53, 53, 53)
                .addGroup(ylaPaneeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ylaPaneeliLayout.createSequentialGroup()
                        .addComponent(lisaaTilitapahtumaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(muunnaTilitapahtumaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(poistaTilitapahtumaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(hallitseTilitapahtumiaLabel))
                .addGap(18, 18, 18)
                .addComponent(Logo)
                .addContainerGap(53, Short.MAX_VALUE))
        );
        ylaPaneeliLayout.setVerticalGroup(
            ylaPaneeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ylaPaneeliLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ylaPaneeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(ylaPaneeliLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Logo))
                    .addGroup(ylaPaneeliLayout.createSequentialGroup()
                        .addComponent(nakymaLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(ylaPaneeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nakymaAlkuKuukausiSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nakymaAlkuVuosiSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nakymaAlkuPaivaSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ylaPaneeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nakymaLoppuKuukausiSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nakymaLoppuVuosiSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nakymaLoppuPaivaSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(ylaPaneeliLayout.createSequentialGroup()
                        .addComponent(hallitseTilitapahtumiaLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(ylaPaneeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(muunnaTilitapahtumaButton, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                            .addComponent(lisaaTilitapahtumaButton, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                            .addComponent(poistaTilitapahtumaButton, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                            .addComponent(meneButton, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE))))
                .addContainerGap())
        );

        sivuPalkki.setMinimumSize(new java.awt.Dimension(200, 100));

        kuvausLabel.setText("Kuvaus");

        summaLabel.setText("Summa");

        paivaLabel.setText("pv");

        kuukausiLabel.setText("kk");

        vuosiLabel.setText("vuosi");

        tallennaButton.setText("Tallenna");
        tallennaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tallennaButtonActionPerformed(evt);
            }
        });

        toistuvaAlkuPaivaLabel.setText("pv");

        toistuvaAlkuPaivaTextField.setEnabled(false);

        toistuvaAlkuKuukausiLabel.setText("kk");

        toistuvaAlkuKuukausiTextField.setEnabled(false);

        toistuvaAlkuVuosiTextField.setEnabled(false);

        toistuvaAlkuVuosiLabel.setText("vuosi");

        toistuvuusCheckBox.setText("Toistuva tapahtuma");
        toistuvuusCheckBox.setEnabled(false);

        toistuvaLoppuKuukausiLabel.setText("kk");

        toistuvaLoppuKuukausiTextField.setEnabled(false);

        toistuvaLoppuPaivaLabel.setText("pv");

        toistuvaLoppuPaivaTextField.setEnabled(false);

        toistuvaLoppuVuosiTextField.setEnabled(false);

        toistuvaLoppuVuosiLabel.setText("vuosi");

        javax.swing.GroupLayout sivuPalkkiLayout = new javax.swing.GroupLayout(sivuPalkki);
        sivuPalkki.setLayout(sivuPalkkiLayout);
        sivuPalkkiLayout.setHorizontalGroup(
            sivuPalkkiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sivuPalkkiLayout.createSequentialGroup()
                .addGroup(sivuPalkkiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tallennaButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(kuvausTextField)
                    .addComponent(summaTextField)
                    .addComponent(kuvausLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(summaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(sivuPalkkiLayout.createSequentialGroup()
                        .addGroup(sivuPalkkiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(toistuvaAlkuPaivaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(toistuvaAlkuPaivaLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(sivuPalkkiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(toistuvaAlkuKuukausiTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(toistuvaAlkuKuukausiLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(sivuPalkkiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(sivuPalkkiLayout.createSequentialGroup()
                                .addComponent(toistuvaAlkuVuosiLabel)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(toistuvaAlkuVuosiTextField)))
                    .addGroup(sivuPalkkiLayout.createSequentialGroup()
                        .addGroup(sivuPalkkiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(toistuvuusCheckBox)
                            .addGroup(sivuPalkkiLayout.createSequentialGroup()
                                .addComponent(paivaLabel)
                                .addGap(29, 29, 29)
                                .addComponent(kuukausiLabel)
                                .addGap(18, 18, 18)
                                .addComponent(vuosiLabel)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(sivuPalkkiLayout.createSequentialGroup()
                        .addComponent(paivaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(kuukausiTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vuosiTextField))
                    .addGroup(sivuPalkkiLayout.createSequentialGroup()
                        .addGroup(sivuPalkkiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(toistuvaLoppuPaivaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(toistuvaLoppuPaivaLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(sivuPalkkiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(toistuvaLoppuKuukausiTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(toistuvaLoppuKuukausiLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(sivuPalkkiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(sivuPalkkiLayout.createSequentialGroup()
                                .addComponent(toistuvaLoppuVuosiLabel)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(toistuvaLoppuVuosiTextField))))
                .addGap(12, 12, 12))
        );
        sivuPalkkiLayout.setVerticalGroup(
            sivuPalkkiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sivuPalkkiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(kuvausLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kuvausTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(summaLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(summaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(sivuPalkkiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(paivaLabel)
                    .addComponent(kuukausiLabel)
                    .addComponent(vuosiLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(sivuPalkkiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(paivaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kuukausiTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vuosiTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(toistuvuusCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(sivuPalkkiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(toistuvaAlkuPaivaLabel)
                    .addComponent(toistuvaAlkuKuukausiLabel)
                    .addComponent(toistuvaAlkuVuosiLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(sivuPalkkiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(toistuvaAlkuPaivaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(toistuvaAlkuKuukausiTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(toistuvaAlkuVuosiTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(sivuPalkkiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(toistuvaLoppuPaivaLabel)
                    .addComponent(toistuvaLoppuKuukausiLabel)
                    .addComponent(toistuvaLoppuVuosiLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(sivuPalkkiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(toistuvaLoppuPaivaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(toistuvaLoppuKuukausiTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(toistuvaLoppuVuosiTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(tallennaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        kuvausTextField.getAccessibleContext().setAccessibleName("");

        yhteenvetoTextArea.setEditable(false);
        yhteenvetoTextArea.setColumns(20);
        yhteenvetoTextArea.setFont(new java.awt.Font("Monospaced", 0, 15)); // NOI18N
        yhteenvetoTextArea.setRows(5);
        yhteenvetoPane.setViewportView(yhteenvetoTextArea);

        javax.swing.GroupLayout paaPaneeliLayout = new javax.swing.GroupLayout(paaPaneeli);
        paaPaneeli.setLayout(paaPaneeliLayout);
        paaPaneeliLayout.setHorizontalGroup(
            paaPaneeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paaPaneeliLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paaPaneeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ylaPaneeli, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(paaPaneeliLayout.createSequentialGroup()
                        .addGroup(paaPaneeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(listausScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                            .addComponent(yhteenvetoPane))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sivuPalkki, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(0, 0, 0))
        );
        paaPaneeliLayout.setVerticalGroup(
            paaPaneeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paaPaneeliLayout.createSequentialGroup()
                .addComponent(ylaPaneeli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(paaPaneeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paaPaneeliLayout.createSequentialGroup()
                        .addComponent(listausScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(yhteenvetoPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sivuPalkki, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );

        getContentPane().add(paaPaneeli, java.awt.BorderLayout.CENTER);

        tiliMenu.setText("Tili");

        uusiMenuItem.setText("Uusi");
        uusiMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uusiMenuItemActionPerformed(evt);
            }
        });
        tiliMenu.add(uusiMenuItem);

        avaaMenuItem.setText("Avaa");
        avaaMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                avaaMenuItemActionPerformed(evt);
            }
        });
        tiliMenu.add(avaaMenuItem);

        tallennaMenuItem.setText("Tallenna");
        tallennaMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tallennaMenuItemActionPerformed(evt);
            }
        });
        tiliMenu.add(tallennaMenuItem);
        tiliMenu.add(menuSeparator1);

        vaihdaTilinNimiMenuItem.setText("Vaihda tilin nimi");
        vaihdaTilinNimiMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vaihdaTilinNimiMenuItemActionPerformed(evt);
            }
        });
        tiliMenu.add(vaihdaTilinNimiMenuItem);
        tiliMenu.add(menuSeparator2);

        poistuMenuItem.setText("Poistu");
        poistuMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                poistuMenuItemActionPerformed(evt);
            }
        });
        tiliMenu.add(poistuMenuItem);

        menuBar.add(tiliMenu);

        setJMenuBar(menuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Tallenna-painikkeen kuuntelija.
     * Muuntaa listauksessa valitun tilitapahtuman tiedot kentissä syötetyiksi
     * tiedoiksi kun käyttäjä painaa Tallenna-painiketta.
     * @param evt Toiminnan laukauseva tapahtuma
     */
    private void tallennaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tallennaButtonActionPerformed
        int valinta = listausList.getSelectedIndex();
        if (valinta != -1) {
            if (listausList.getModel().getElementAt(valinta) instanceof YksittainenTilitapahtuma) {
                YksittainenTilitapahtuma tapahtuma = (YksittainenTilitapahtuma) listausList.getModel().getElementAt(valinta);
                
                tapahtuma.setKuvaus(kuvausTextField.getText());
                
                if (summaTextField.getText().length() <= GUI.SUURIN_NUMERAALIN_PITUUS){
                    tapahtuma.getSumma().setSummaString(summaTextField.getText());
                }
                
                Paivamaara uusiAikaleima = muunnaPaivamaaraksi(
                        vuosiTextField.getText(),
                        kuukausiTextField.getText(),
                        paivaTextField.getText()
                        );
                if (uusiAikaleima != null){
                    tapahtuma.setAikaleima(uusiAikaleima);
                }

            } else if (listausList.getModel().getElementAt(valinta) instanceof ToistuvaTilitapahtuma) {
                ToistuvaTilitapahtuma tapahtuma = (ToistuvaTilitapahtuma) listausList.getModel().getElementAt(valinta);
                
                tapahtuma.setKuvaus(kuvausTextField.getText());
                
                if (summaTextField.getText().length() <= GUI.SUURIN_NUMERAALIN_PITUUS){
                    tapahtuma.getSumma().setSummaString(summaTextField.getText());
                }
                
                Paivamaara uusiAlkuPvm = muunnaPaivamaaraksi(
                        toistuvaAlkuVuosiTextField.getText(),
                        toistuvaAlkuKuukausiTextField.getText(),
                        toistuvaAlkuPaivaTextField.getText()
                        );
                if (uusiAlkuPvm != null){
                    tapahtuma.setAlkupvm(uusiAlkuPvm);
                }
                
                Paivamaara uusiLoppuPvm = muunnaPaivamaaraksi(
                        toistuvaLoppuVuosiTextField.getText(),
                        toistuvaLoppuKuukausiTextField.getText(),
                        toistuvaLoppuPaivaTextField.getText()
                        );
                if (uusiLoppuPvm != null){
                    tapahtuma.setLoppupvm(uusiLoppuPvm);
                }
            }
            paivitaListaus();
            listausList.setSelectedIndex(valinta);
        }
    }//GEN-LAST:event_tallennaButtonActionPerformed

    /**
     * Tapahtumalistauksen kuuntelija.
     * Asettaa sivupalkin kenttien arvot ja kenttien enabled-attribuutin valitun
     * tapahtuman tyypin mukaan.
     * @param evt Toiminnan laukauseva tapahtuma.
     */
    private void listausListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listausListValueChanged
        int valinta = listausList.getSelectedIndex();
        if (valinta != -1) {
            if (listausList.getModel().getElementAt(valinta) instanceof YksittainenTilitapahtuma) {
                if (toistuvuusCheckBox.isSelected()) {
                    toistuvuusCheckBox.setSelected(false);
                    vaihdaToistuvuusCheckBoxValinta();
                }
                YksittainenTilitapahtuma tapahtuma = (YksittainenTilitapahtuma) listausList.getModel().getElementAt(valinta);
                tyhjennaTapahtumanTietoKentat();
                summaTextField.setText(tapahtuma.getSumma().getSummaString());
                kuvausTextField.setText(tapahtuma.getKuvaus());
                paivaTextField.setText("" + tapahtuma.getAikaleima().get(Calendar.DAY_OF_MONTH));
                kuukausiTextField.setText("" + (tapahtuma.getAikaleima().get(Calendar.MONTH) + 1));
                vuosiTextField.setText("" + tapahtuma.getAikaleima().get(Calendar.YEAR));
            } 
            
            else if (listausList.getModel().getElementAt(valinta) instanceof ToistuvaTilitapahtuma) {
                if (!toistuvuusCheckBox.isSelected()) {
                    toistuvuusCheckBox.setSelected(true);
                    vaihdaToistuvuusCheckBoxValinta();
                }
                ToistuvaTilitapahtuma tapahtuma = (ToistuvaTilitapahtuma) listausList.getModel().getElementAt(valinta);
                tyhjennaTapahtumanTietoKentat();
                summaTextField.setText(tapahtuma.getSumma().getSummaString());
                kuvausTextField.setText(tapahtuma.getKuvaus());
                toistuvaAlkuVuosiTextField.setText("" + tapahtuma.getAlkupvm().get(Calendar.YEAR));
                toistuvaAlkuKuukausiTextField.setText("" + (tapahtuma.getAlkupvm().get(Calendar.MONTH) + 1));
                toistuvaAlkuPaivaTextField.setText("" + tapahtuma.getAlkupvm().get(Calendar.DAY_OF_MONTH));
                toistuvaLoppuVuosiTextField.setText("" + tapahtuma.getLoppupvm().get(Calendar.YEAR));
                toistuvaLoppuKuukausiTextField.setText("" + (tapahtuma.getLoppupvm().get(Calendar.MONTH) + 1));
                toistuvaLoppuPaivaTextField.setText("" + tapahtuma.getLoppupvm().get(Calendar.DAY_OF_MONTH));
            } 
            
            else if (listausList.getModel().getElementAt(valinta) instanceof String) {
                tyhjennaTapahtumanTietoKentat();
            }
        }
    }//GEN-LAST:event_listausListValueChanged

    /**
     * Uusi tili -painikkeen kuuntelija.
     * Varmistaa käyttäjältä haluaako tämä varmasti luoda uuden tilin. Mikäli
     * käyttäjä vastaa positiivisesti, luo uuden tilin.
     * @param evt Toiminnan laukauseva tapahtuma.
     */
    private void uusiMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uusiMenuItemActionPerformed
        int vastaus = JOptionPane.showConfirmDialog(null, "Haluatko varmasti luoda uuden tilin?\nNykyisen tilin tallentamattomat tiedot menetetään peruuttamattomasti.", "Oletko varma?", JOptionPane.YES_NO_OPTION);
        if (vastaus == JOptionPane.YES_OPTION) {
            this.tili = new Tili("Uusi tili");
        }
        paivitaListaus();
        paivitaOtsikko();
    }//GEN-LAST:event_uusiMenuItemActionPerformed

    /**
     * Avaa tili -painikkeen kuuntelija.
     * Avaa tiedostonvalinta -dialogin ja lataa käyttäjän valitseman tilin tiedostosta.
     * @param evt Toiminnan laukauseva tapahtuma.
     */
    private void avaaMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_avaaMenuItemActionPerformed
        JFileChooser dialogi = new JFileChooser();
        int vastaus = dialogi.showOpenDialog(this);
        if (vastaus == JFileChooser.APPROVE_OPTION) {
            Tili ladattu = io.lataaTili(dialogi.getSelectedFile());
            if (ladattu != null) {
                this.tili = ladattu;
            }
        }
        paivitaListaus();
        paivitaOtsikko();
    }//GEN-LAST:event_avaaMenuItemActionPerformed

    /**
     * Tallenna tili-painikkeen kuuntelija.
     * Avaa tiedostonvalinta -dialogin ja tallentaa tilin käyttäjän valitsemaan tiedostoon.
     * @param evt Toiminnan laukauseva tapahtuma.
     */
    private void tallennaMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tallennaMenuItemActionPerformed
        JFileChooser dialogi = new JFileChooser();
        int vastaus = dialogi.showSaveDialog(this);
        if (vastaus == JFileChooser.APPROVE_OPTION) {
            File tiedosto = dialogi.getSelectedFile();
            io.tallennaTili(this.tili, tiedosto);
        }
    }//GEN-LAST:event_tallennaMenuItemActionPerformed

    /**
     * Poistu -painikkeen kuuntelija.
     * Sulkee ohjelman. 
     * @param evt Toiminnan laukauseva tapahtuma.
     */
    private void poistuMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_poistuMenuItemActionPerformed
        int vastaus = JOptionPane.showConfirmDialog(null, "Haluatko varmasti lopettaa ohjelman suorituksen?\nKaikki tallentamattomat tiedot menetetään peruuttamattomasti.", "Oletko varma?", JOptionPane.YES_NO_OPTION);
        if (vastaus == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_poistuMenuItemActionPerformed

    /**
     * Poista tilitapahtuma -painikkeen kuuntelija.
     * Varmistaa toiminnan käyttäjältä ja poistaa valitun tilitapahtuman.
     * @param evt Toiminnan laukauseva tapahtuma.
     */
    private void poistaTilitapahtumaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_poistaTilitapahtumaButtonActionPerformed
        int indeksi = listausList.getSelectedIndex();
        if (indeksi != -1) {
            if (listausList.getModel().getElementAt(indeksi) instanceof YksittainenTilitapahtuma) {
                YksittainenTilitapahtuma tapahtuma = (YksittainenTilitapahtuma) listausList.getModel().getElementAt(indeksi);
                int vastaus = JOptionPane.showConfirmDialog(null, "Haluatko varmasti poistaa tilitapahtuman?\nPoistettua tilitapahtumaa ei voi palauttaa myöhemmin.", "Oletko varma?", JOptionPane.YES_NO_OPTION);
                if (vastaus == JOptionPane.YES_OPTION) {
                    tili.poistaTilitapahtuma(tapahtuma);
                    paivitaListaus();
                }
            } else if (listausList.getModel().getElementAt(indeksi) instanceof ToistuvaTilitapahtuma) {
                ToistuvaTilitapahtuma tapahtuma = (ToistuvaTilitapahtuma) listausList.getModel().getElementAt(indeksi);
                int vastaus = JOptionPane.showConfirmDialog(null,
                        "Haluatko varmasti poistaa toistuvan tilitapahtuman?\n"
                        + "Poistettua tilitapahtumaa ei voi palauttaa myöhemmin.\n"
                        + "\n"
                        + "Voit muuntaa toistuvan tilitapahtuman yksittäisiksi tilitapahtumiksi \"muunna\" -toiminnolla.",
                        "Oletko varma?",
                        JOptionPane.YES_NO_OPTION);
                if (vastaus == JOptionPane.YES_OPTION) {
                    tili.poistaToistuvaTilitapahtuma(tapahtuma);
                    paivitaListaus();
                }
            }
        }

    }//GEN-LAST:event_poistaTilitapahtumaButtonActionPerformed

    /**
     * Lisää tilitapahtuma -painikkeen kuuntelija.
     * Luo tilille uuden tilitapahtuman.
     * @param evt Toiminnan laukauseva tapahtuma.
     */
    private void lisaaTilitapahtumaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lisaaTilitapahtumaButtonActionPerformed
        YksittainenTilitapahtuma tapahtuma = new YksittainenTilitapahtuma(
                "Uusi tilitapahtuma",
                new Summa(0),
                new Paivamaara(alkuPvm.get(Calendar.YEAR), alkuPvm.get(Calendar.MONTH), 1));

        tili.lisaaTilitapahtuma(tapahtuma);
        paivitaListaus();
    }//GEN-LAST:event_lisaaTilitapahtumaButtonActionPerformed

    /**
     * Muunna tilitapahtuma -painikkeen kuuntelija.
     * Muuntaa tilitapahtuman toisen tyyppiseksi.
     * YksittäinenTilitapahtuma muuntuu ToistuvaTilitapahtuma:ksi. ToistuvaTilitapahtuma
     * muuntuu YksittäinenTilitapahtuma:ksi joko kaikkien tapahtumakertojensa tai vain
     * näkyvän aikavälin osalta käyttäjän syötteestä riippuen.
     * @param evt Toiminnan laukauseva tapahtuma.
     */
    private void muunnaTilitapahtumaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_muunnaTilitapahtumaButtonActionPerformed
        int indeksi = listausList.getSelectedIndex();
        if (indeksi != -1) {
            if (listausList.getModel().getElementAt(indeksi) instanceof YksittainenTilitapahtuma) {
                YksittainenTilitapahtuma tapahtuma = (YksittainenTilitapahtuma) listausList.getModel().getElementAt(indeksi);
                tili.lisaaToistuvaTilitapahtuma(new ToistuvaTilitapahtuma(
                        tapahtuma.getKuvaus(),
                        tapahtuma.getSumma(),
                        tapahtuma.getAikaleima(),
                        tapahtuma.getAikaleima()));
                tili.poistaTilitapahtuma(tapahtuma);
            } else if (listausList.getModel().getElementAt(indeksi) instanceof ToistuvaTilitapahtuma) {
                ToistuvaTilitapahtuma tapahtuma = (ToistuvaTilitapahtuma) listausList.getModel().getElementAt(indeksi);
                int vastaus = JOptionPane.showConfirmDialog(null,
                        "Haluatko muuntaa vain näkyvien kuukausien tapahtumat?\n\n",
                        "Muunnetaanko vain näkyvien kuukausien osalta?",
                        JOptionPane.YES_NO_CANCEL_OPTION);
                
                if (vastaus == JOptionPane.YES_OPTION || vastaus == JOptionPane.NO_OPTION) {
                    Paivamaara ylaRaja = null;
                    Paivamaara alaRaja = null;
                    if (vastaus == JOptionPane.YES_OPTION) {
                        alaRaja = (Paivamaara) this.alkuPvm.clone();
                        ylaRaja = (Paivamaara) this.loppuPvm.clone();
                    }
                    tili.konvertoiJaPoistaToistuvaTilitapahtuma(tapahtuma, alaRaja, ylaRaja);
                }
            }
            paivitaListaus();
        }
    }//GEN-LAST:event_muunnaTilitapahtumaButtonActionPerformed

    /**
     * Mene -painikkeen kuuntelija.
     * Vaihtaa näytettävän aikavälin.
     * @param evt Toiminnan laukauseva tapahtuma.
     */
    private void meneButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_meneButtonActionPerformed
        Paivamaara uusiLoppuPvm = new Paivamaara();
        Paivamaara uusiAlkuPvm = new Paivamaara();
        
        if(asetaPaivamaara(uusiAlkuPvm, nakymaAlkuVuosiSpinner, nakymaAlkuKuukausiSpinner, nakymaAlkuPaivaSpinner)){
            if(asetaPaivamaara(uusiLoppuPvm, nakymaLoppuVuosiSpinner, nakymaLoppuKuukausiSpinner, nakymaLoppuPaivaSpinner)){
                if(!uusiAlkuPvm.after(uusiLoppuPvm)){
                    this.alkuPvm = uusiAlkuPvm;
                    this.loppuPvm = uusiLoppuPvm;
                }
            }
        }

        //Asetetaan spinnerit todelliseen aikaan
        nakymaAlkuVuosiSpinner.setValue(alkuPvm.get(Calendar.YEAR));
        nakymaAlkuKuukausiSpinner.setValue(alkuPvm.get(Calendar.MONTH) +1);   
        nakymaAlkuPaivaSpinner.setValue(alkuPvm.get(Calendar.DAY_OF_MONTH));
        nakymaLoppuVuosiSpinner.setValue(loppuPvm.get(Calendar.YEAR));
        nakymaLoppuKuukausiSpinner.setValue(loppuPvm.get(Calendar.MONTH) +1);   
        nakymaLoppuPaivaSpinner.setValue(loppuPvm.get(Calendar.DAY_OF_MONTH));   

        paivitaOtsikko();
        paivitaListaus();
    }//GEN-LAST:event_meneButtonActionPerformed

    /**
     * Vaihda tilin nimi -painikkeen kuuntelija.
     * Avaa dialogin jolla käyttäjä voi uudelleennimetä tilin.
     * @param evt Toiminnan laukauseva tapahtuma.
     */
    private void vaihdaTilinNimiMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vaihdaTilinNimiMenuItemActionPerformed
        String uusiNimi = JOptionPane.showInputDialog(null, "Syötä tilin uusi nimi", "Vaihda tilin nimi", 1);
        if (uusiNimi != null){
            tili.setNimi(uusiNimi);
        }
        paivitaOtsikko();
        paivitaListaus();
    }//GEN-LAST:event_vaihdaTilinNimiMenuItemActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Logo;
    private javax.swing.JMenuItem avaaMenuItem;
    private javax.swing.JLabel hallitseTilitapahtumiaLabel;
    private javax.swing.JLabel kuukausiLabel;
    private javax.swing.JTextField kuukausiTextField;
    private javax.swing.JLabel kuvausLabel;
    private javax.swing.JTextField kuvausTextField;
    private javax.swing.JButton lisaaTilitapahtumaButton;
    private javax.swing.JList listausList;
    private javax.swing.JScrollPane listausScrollPane;
    private javax.swing.JButton meneButton;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JPopupMenu.Separator menuSeparator1;
    private javax.swing.JPopupMenu.Separator menuSeparator2;
    private javax.swing.JButton muunnaTilitapahtumaButton;
    private javax.swing.JSpinner nakymaAlkuKuukausiSpinner;
    private javax.swing.JSpinner nakymaAlkuPaivaSpinner;
    private javax.swing.JSpinner nakymaAlkuVuosiSpinner;
    private javax.swing.JLabel nakymaLabel;
    private javax.swing.JSpinner nakymaLoppuKuukausiSpinner;
    private javax.swing.JSpinner nakymaLoppuPaivaSpinner;
    private javax.swing.JSpinner nakymaLoppuVuosiSpinner;
    private javax.swing.JPanel paaPaneeli;
    private javax.swing.JLabel paivaLabel;
    private javax.swing.JTextField paivaTextField;
    private javax.swing.JButton poistaTilitapahtumaButton;
    private javax.swing.JMenuItem poistuMenuItem;
    private javax.swing.JPanel sivuPalkki;
    private javax.swing.JLabel summaLabel;
    private javax.swing.JTextField summaTextField;
    private javax.swing.JButton tallennaButton;
    private javax.swing.JMenuItem tallennaMenuItem;
    private javax.swing.JMenu tiliMenu;
    private javax.swing.JLabel toistuvaAlkuKuukausiLabel;
    private javax.swing.JTextField toistuvaAlkuKuukausiTextField;
    private javax.swing.JLabel toistuvaAlkuPaivaLabel;
    private javax.swing.JTextField toistuvaAlkuPaivaTextField;
    private javax.swing.JLabel toistuvaAlkuVuosiLabel;
    private javax.swing.JTextField toistuvaAlkuVuosiTextField;
    private javax.swing.JLabel toistuvaLoppuKuukausiLabel;
    private javax.swing.JTextField toistuvaLoppuKuukausiTextField;
    private javax.swing.JLabel toistuvaLoppuPaivaLabel;
    private javax.swing.JTextField toistuvaLoppuPaivaTextField;
    private javax.swing.JLabel toistuvaLoppuVuosiLabel;
    private javax.swing.JTextField toistuvaLoppuVuosiTextField;
    private javax.swing.JCheckBox toistuvuusCheckBox;
    private javax.swing.JMenuItem uusiMenuItem;
    private javax.swing.JMenuItem vaihdaTilinNimiMenuItem;
    private javax.swing.JLabel vuosiLabel;
    private javax.swing.JTextField vuosiTextField;
    private javax.swing.JScrollPane yhteenvetoPane;
    private javax.swing.JTextArea yhteenvetoTextArea;
    private javax.swing.JPanel ylaPaneeli;
    // End of variables declaration//GEN-END:variables

    /**
     * Päivittää ohjelman otsikkorivin.
     */
    private void paivitaOtsikko() {
        this.setTitle("Buddy - pientalouden budjetoija - "
                + this.tili.getNimi()
                + " - "
                + this.alkuPvm.get(Calendar.DAY_OF_MONTH)
                + "/"
                + (this.alkuPvm.get(Calendar.MONTH) + 1)
                + "/"
                + this.alkuPvm.get(Calendar.YEAR)
                + " - "
                + this.loppuPvm.get(Calendar.DAY_OF_MONTH)
                + "/"
                + (this.loppuPvm.get(Calendar.MONTH) + 1)
                + "/"
                + this.loppuPvm.get(Calendar.YEAR));
    }

    /**
     * Päivittää tilitapahtumalistauksen.
     */
    private void paivitaListaus() {
        Paivamaara alku = (Paivamaara) alkuPvm.clone();
        Paivamaara loppu = (Paivamaara) loppuPvm.clone();

        ArrayList<Object> tapahtumat = new ArrayList();
        
        ArrayList<ToistuvaTilitapahtuma> toistuvatTapahtumat = tili.getToistuvatTilitapahtumatAjalta(alku, loppu);
        ArrayList<YksittainenTilitapahtuma> yksittaisetTapahtumat = tili.getTilitapahtumatAjalta(alku, loppu);
        
        if (!toistuvatTapahtumat.isEmpty()){
            tapahtumat.add("          Toistuvat tilitapahtumat");
        }
        tapahtumat.addAll(toistuvatTapahtumat);
        if (!toistuvatTapahtumat.isEmpty() && !yksittaisetTapahtumat.isEmpty()){
            tapahtumat.add(" ");
        }
        if (!yksittaisetTapahtumat.isEmpty()){
            tapahtumat.add("          Yksittäiset tilitapahtumat");
        }
        tapahtumat.addAll(yksittaisetTapahtumat);
        
        listausList.setListData(tapahtumat.toArray());
        
        paivitaYhteenveto();
    }

    /**
     * Vaihtaa sivupalkin elementtien enabled-asetuksen ryhmittäin päinvastaiseksi.
     */
    private void vaihdaToistuvuusCheckBoxValinta() {
        paivaTextField.setEnabled(!paivaTextField.isEnabled());
        kuukausiTextField.setEnabled(!kuukausiTextField.isEnabled());
        vuosiTextField.setEnabled(!vuosiTextField.isEnabled());

        toistuvaAlkuPaivaTextField.setEnabled(!toistuvaAlkuPaivaTextField.isEnabled());
        toistuvaAlkuKuukausiTextField.setEnabled(!toistuvaAlkuKuukausiTextField.isEnabled());
        toistuvaAlkuVuosiTextField.setEnabled(!toistuvaAlkuVuosiTextField.isEnabled());
        toistuvaLoppuPaivaTextField.setEnabled(!toistuvaLoppuPaivaTextField.isEnabled());
        toistuvaLoppuKuukausiTextField.setEnabled(!toistuvaLoppuKuukausiTextField.isEnabled());
        toistuvaLoppuVuosiTextField.setEnabled(!toistuvaLoppuVuosiTextField.isEnabled());
    }

    /**
     * Tarkistaa onko syöte validi numeraali.
     * Syöte on validi jos se koostuu vain yhdestä tai useammasta numerosta ja on
     * lyhyempi kuin SUURIN_NUMERAALIN_PITUUS.
     * @param teksti Tarkistettava teksti.
     * @return Validiutta kuvaava boolean-arvo.
     */
    private boolean onNumeraali(String teksti) {
        if (teksti.matches("[0-9]+") && teksti.length() < GUI.SUURIN_NUMERAALIN_PITUUS) {
            return true;
        }
        return false;
    }

    /**
     * Tyhjentää sivupalkin kentät.
     */
    private void tyhjennaTapahtumanTietoKentat() {
        summaTextField.setText("");
        kuvausTextField.setText("");
        paivaTextField.setText("");
        kuukausiTextField.setText("");
        vuosiTextField.setText("");
        toistuvaAlkuVuosiTextField.setText("");
        toistuvaAlkuKuukausiTextField.setText("");
        toistuvaAlkuPaivaTextField.setText("");
        toistuvaLoppuVuosiTextField.setText("");
        toistuvaLoppuKuukausiTextField.setText("");
        toistuvaLoppuPaivaTextField.setText("");
    }

    /**
     * Päivittää yhteenveto-alueen tiedot.
     */
    private void paivitaYhteenveto() {
        this.yhteenvetoTextArea.setText(new Yhteenveto(tili).yhteenvetoAikavalilta(alkuPvm, loppuPvm));
    }

    /**
     * Asettaa päivämäärän.
     * Tarkistaa myös päivämäärän järkevyyden seuraavin kriteerein:
     * Päivä -arvo ei voi olla suurempi kuin mitä kuukaudessa on päiviä.
     * Vuosi ei voi olla 0 tai pienempi.
     * @param pvm Päivämäärä joka päivitetään uusin arvoin.
     * @param vuosiSpinner JSpinner josta saadaan vuosi.
     * @param kuukausiSpinner JSpinner josta saadaan kuukausi.
     * @param paivaSpinner JSpinner josta saadaan päivä.
     * @return Operaation onnistumista kuvaava boolean -arvo.
     */
    private boolean asetaPaivamaara(Paivamaara pvm, JSpinner vuosiSpinner, JSpinner kuukausiSpinner, JSpinner paivaSpinner) {
        try{
            int vuosi = (Integer) vuosiSpinner.getValue();
            int kuukausi = (Integer) kuukausiSpinner.getValue() - 1;
            int paiva = (Integer) paivaSpinner.getValue();
            
            if (vuosi < 0) { return false; }
            
            vuosi = (vuosi > 0) ? vuosi : 1;
            pvm.set(Calendar.YEAR, vuosi);

            pvm.set(Calendar.MONTH, kuukausi);

            paiva = (paiva < pvm.getActualMaximum(Calendar.DAY_OF_MONTH)) ? paiva : pvm.getActualMaximum(Calendar.DAY_OF_MONTH);
            pvm.set(Calendar.DAY_OF_MONTH, paiva);
            
            return true;
        } catch (Exception e){
            return false;
        }
    }

    private Paivamaara muunnaPaivamaaraksi(String vuosi, String kuukausi, String paiva) {
        if (onNumeraali(vuosi) && onNumeraali(kuukausi) && onNumeraali(paiva)) {
            return new Paivamaara(
                    Integer.parseInt(vuosi),
                    Integer.parseInt(kuukausi) - 1,
                    Integer.parseInt(paiva));
        }
        return null;
    }
}
