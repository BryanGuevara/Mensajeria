/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ventanas;

import clases.DBConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.swing.JOptionPane;
import bryanDev.util.config.GlobalStyle;
import javax.swing.SwingUtilities;

/**
 *
 * @author bryan
 */
public class Mensajes extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Mensajes.class.getName());

    GlobalStyle global = new GlobalStyle();
    String Userguid;
    String lastMenssage;
    private Thread hiloMensajes;

    public Mensajes() {
        initComponents();
    }

    public Mensajes(String guidUsuario, String nombreUsuario) {
        initComponents();

        Userguid = guidUsuario;

        global.Frame().configureFrame(this, "Mensajeria - " + nombreUsuario, "");
        global.TextArea().style(AreaMensaje, 15);
        global.Button().style(BtnEnviar);

        iniciarHiloDeMensajes();

        AreaMensaje.requestFocusInWindow();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ScrollMensaje = new javax.swing.JScrollPane();
        PanelChat = new javax.swing.JTextPane();
        BtnEnviar = new javax.swing.JButton();
        ScrollEnviar = new javax.swing.JScrollPane();
        AreaMensaje = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PanelChat.setEditable(false);
        PanelChat.setContentType("text/htm"); // NOI18N
        ScrollMensaje.setViewportView(PanelChat);
        PanelChat.getAccessibleContext().setAccessibleDescription("text/html");

        BtnEnviar.setText("Enviar");
        BtnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEnviarActionPerformed(evt);
            }
        });

        AreaMensaje.setColumns(20);
        AreaMensaje.setRows(5);
        ScrollEnviar.setViewportView(AreaMensaje);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ScrollEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 9, Short.MAX_VALUE))
                    .addComponent(ScrollMensaje, javax.swing.GroupLayout.Alignment.TRAILING)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(ScrollMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ScrollEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEnviarActionPerformed
        String mensaje = AreaMensaje.getText().trim();

        if (mensaje.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un mensaje.");
            return;
        }

        try (Connection cn = DBConexion.conexion()) {
            String sql = "INSERT INTO mensaje (GUID, time, userGUID, mensaje) "
                    + "VALUES (UUID(), CURRENT_TIMESTAMP(), ?, ?)";
            try (PreparedStatement pst = cn.prepareStatement(sql)) {
                pst.setString(1, Userguid);
                pst.setString(2, mensaje);
                pst.executeUpdate();

                AreaMensaje.setText("");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al enviar el mensaje:\n" + e.getMessage());
        }

        ObtenerMensajes();
        iniciarHiloDeMensajes();

    }//GEN-LAST:event_BtnEnviarActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Mensajes().setVisible(true));
    }

    public void ObtenerMensajes() {
        new Thread(() -> {
            String mensajes = "<html>"
                    + "<head>"
                    + "<style>"
                    + "body { background-color: #121212; color: #f1f1f1; font-family: sans-serif; padding: 10px; }"
                    + ".chat-box { background-color: #1e1e1e; border-radius: 8px; padding: 10px; }"
                    + ".message { margin: 1px 0; padding: 10px; border-radius: 6px; background-color: #0e0e0e; color: #e0e0e0; border-left: 4px solid #444; }"
                    + ".Mymessage { margin: 1px 0; padding: 10px; border-radius: 6px; background-color: #1a2a40; color: #cce6ff; border-left: 4px solid #3399ff; }"
                    + ".Sysmessage { margin: 1px 0; padding: 10px; border-radius: 6px; background-color: #2a0e12; color: #ffcccc; border-left: 4px solid #ff4d4d; }"
                    + ".sender { font-weight: bold; color: #aaa; display: block; margin-bottom: 4px; font-size: 90%; }"
                    + "</style>"
                    + "</head>"
                    + "<body>";

            try (Connection cn = DBConexion.conexion()) {
                String sql = "SELECT "
                        + "    mensaje.time, "
                        + "    usuarios.usuario AS nombre_usuario, "
                        + "    mensaje.mensaje, "
                        + "    mensaje.GUID AS mensaje_guid, "
                        + "    mensaje.userGUID AS usuario_guid "
                        + "FROM mensaje "
                        + "JOIN usuarios ON mensaje.userGUID = usuarios.GUID "
                        + "ORDER BY mensaje.time ASC";

                PreparedStatement pst = cn.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();

                DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
                DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM hh:mm a", new Locale("es", "ES"));

                while (rs.next()) {
                    String rawTime = rs.getString("time");
                    LocalDateTime fechaHora = LocalDateTime.parse(rawTime, inputFormatter);
                    String time = fechaHora.format(outputFormatter).toLowerCase();

                    lastMenssage = rs.getString("mensaje_guid");

                    String usuario = rs.getString("nombre_usuario");
                    String guidUsuario = rs.getString("usuario_guid");
                    String mensaje = escaparHTML(rs.getString("mensaje"));

                    if (guidUsuario.equals(Userguid)) {
                        mensajes += "  <div class='Mymessage'>";
                    } else {
                        mensajes += "  <div class='message'>";
                    }

                    mensajes += "    <span class='sender' title='" + guidUsuario + "'>" + usuario + " - " + time + "</span><br>"
                            + "    " + mensaje
                            + "  </div>";
                }

            } catch (Exception e) {
                System.err.println("Error al cargar los Mensajes: " + e.getMessage());
            }

            mensajes += "</body>"
                    + "</html>";

            String contenidoFinal = mensajes;
            SwingUtilities.invokeLater(() -> {
                PanelChat.setContentType("text/html");
                PanelChat.setText(contenidoFinal);
                PanelChat.setCaretPosition(PanelChat.getDocument().getLength());
            });
        }).start();
    }

    public void iniciarHiloDeMensajes() {
        if (hiloMensajes != null && hiloMensajes.isAlive()) {
            return;
        }

        hiloMensajes = new Thread(() -> {
            while (true) {
                try (Connection cn = DBConexion.conexion()) {
                    String sql = "SELECT GUID FROM mensaje ORDER BY time DESC LIMIT 1";
                    PreparedStatement pst = cn.prepareStatement(sql);
                    ResultSet rs = pst.executeQuery();

                    if (rs.next()) {
                        String guidNuevo = rs.getString("GUID");

                        if (!guidNuevo.equals(lastMenssage)) {
                            ObtenerMensajes();
                        }
                    }
                } catch (Exception e) {
                    System.err.println("Error en hilo de mensajes: " + e.getMessage());
                }

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ie) {
                    System.out.println("Hilo de mensajes interrumpido.");
                    break;
                }
            }
        });

        hiloMensajes.setDaemon(true);
        hiloMensajes.start();
    }

    private String escaparHTML(String texto) {
        return texto.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;")
                .replace("\n", "<br>");
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea AreaMensaje;
    private javax.swing.JButton BtnEnviar;
    private javax.swing.JTextPane PanelChat;
    private javax.swing.JScrollPane ScrollEnviar;
    private javax.swing.JScrollPane ScrollMensaje;
    // End of variables declaration//GEN-END:variables
}
