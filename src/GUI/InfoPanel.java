package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.HashMap;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import service.domain.DataObject;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author kavan
 */
public class InfoPanel extends JPanel {

    /**
     * Creates new form InfoDesign
     */
    // Variables declaration - do not modify
    private JLabel titleLb;
    private JLabel plotLb;
    private JLabel rateInfoLb;
    private JLabel playLb;
    private JLabel typeLb;
    private JLabel genreLb;
    private JLabel directLb;
    private JLabel actorLb;
    private JLabel mpaaLb;
    private JLabel starLb;
    private JLabel posterLb;
    private JPanel plotPanel;
    private JPanel posterPanel;
    private JPanel bottomPanel;
    private JLabel processLb;
    private boolean proc_bin;
    // End of variables declaration

    public InfoPanel() {
        proc_bin = true;
        initComponents();
    }

    public void setInProcess() {
        if (this.proc_bin) {
            this.proc_bin = false;
        } else {
            this.proc_bin = true;
        }
       
        this.removeAll();
        initInProcess();
        this.updateUI();

    }

    public void setInfo(DataObject info, XGUI_Info_Parser parser) {

        this.removeAll();
        initCompsWithValues(parser.getValues(info));
        this.updateUI();

    }

    private void initInProcess() {
        String iconStr = "/img/process/down.png";
        if (proc_bin) {
            iconStr = "/img/process/up.png";
        }
        JPanel proc_panel = new JPanel();
        processLb = new JLabel();
        ImageIcon icon = new ImageIcon(getClass().getResource(iconStr));
        processLb.setIcon(icon);      
        proc_panel.add(processLb);
        this.setLayout(new BorderLayout());
        this.add(proc_panel, BorderLayout.CENTER);
        this.setMinimumSize(new Dimension(600, 600));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {

        posterPanel = new JPanel();
        bottomPanel = new JPanel();
        plotPanel = new JPanel();

        titleLb = new JLabel();
        plotLb = new JLabel();
        genreLb = new JLabel();
        directLb = new JLabel();
        actorLb = new JLabel();
        typeLb = new JLabel();
        mpaaLb = new JLabel();

        starLb = new JLabel();
        rateInfoLb = new JLabel();
        playLb = new JLabel();
        posterLb = new JLabel();


        setPosterPanelLayout();
        setBottomPanelLayout();
        setInfoPanelLayout();

    }

    private void initCompsWithValues(HashMap<String, String> values) {


        posterPanel = new JPanel();
        bottomPanel = new JPanel();
        plotPanel = new JPanel();

        titleLb = new JLabel(values.get("title"));
        plotLb = new JLabel(values.get("plot"));
        genreLb = new JLabel(values.get("genre"));
        directLb = new JLabel(values.get("director"));
        actorLb = new JLabel(values.get("actors"));
        typeLb = new JLabel(values.get("type"));
        mpaaLb = getMpaaLabel(values.get("mpaa"));
        starLb = new JLabel("star");
        rateInfoLb = new JLabel(values.get("rateInfo"));
        playLb = new JLabel("play");
        if (values.get("poster").equalsIgnoreCase(new XGUI_Info_Parser().inDefaultHtmlTag("sad-face"))) {
            posterLb = getSadLabel();
        } else {
            posterLb = new JLabel(values.get("poster"));
        }
        setPlotPanelLayout();
        setPosterPanelLayout();
        setBottomPanelLayout();
        setInfoPanelLayout();
    }

    private JLabel getSadLabel() {
        JLabel lb = new JLabel();
        Icon icon = new ImageIcon(getClass().getResource("/img/sad-face2.png"));
        lb.setIcon(icon);
        lb.setMinimumSize(new Dimension(320, 320));
        return lb;
    }

    private JLabel getMpaaLabel(String mpaa) {
        JLabel lb = new JLabel();
        Icon icon = null;
        if (mpaa.equalsIgnoreCase(new XGUI_Info_Parser().inDefaultHtmlTag("pg13"))) {
            icon = new ImageIcon(getClass().getResource("/img/mpaa/mpaa_pg13_res.jpg"));
        }
        if (mpaa.equalsIgnoreCase(new XGUI_Info_Parser().inDefaultHtmlTag("pg"))) {
            icon = new ImageIcon(getClass().getResource("/img/mpaa/mpaa_pg_res.jpg"));
        }
        if (mpaa.equalsIgnoreCase(new XGUI_Info_Parser().inDefaultHtmlTag("r"))) {
            icon = new ImageIcon(getClass().getResource("/img/mpaa/mpaa_r_res.jpg"));
        }
        if (mpaa.equalsIgnoreCase(new XGUI_Info_Parser().inDefaultHtmlTag("g"))) {
            icon = new ImageIcon(getClass().getResource("/img/mpaa/mpaa_g_res.jpg"));
        }
        if (mpaa.equalsIgnoreCase(new XGUI_Info_Parser().inDefaultHtmlTag("nc17"))) {
            icon = new ImageIcon(getClass().getResource("/img/mpaa/mpaa_nc17_res.jpg"));
        }
        if (icon != null) {
            lb.setIcon(icon);
        }
//        lb.setMaximumSize(new Dimension(300, 116));
        return lb;
    }

    private void setInfoPanelLayout() {

        this.setLayout(new BorderLayout());
        this.add(posterPanel, BorderLayout.NORTH);
        this.add(plotPanel, BorderLayout.WEST);
        this.add(bottomPanel, BorderLayout.PAGE_END);
        this.setMinimumSize(new Dimension(600, 600));


    }

    private void setPlotPanelLayout() {
        plotPanel.setLayout(new BorderLayout());
        plotPanel.add(plotLb, BorderLayout.CENTER);
    }

    private void setPosterPanelLayout() {

        JPanel rPane = new JPanel();
        JPanel lPane = new JPanel();

        posterPanel.setLayout(new BorderLayout());
        rPane.setLayout(new BorderLayout());
        rPane.add(posterLb, BorderLayout.CENTER);

        lPane.setLayout(new BoxLayout(lPane, BoxLayout.Y_AXIS));
        lPane.add(Box.createRigidArea(new Dimension(0, 5)));
        lPane.add(titleLb);
        lPane.add(Box.createRigidArea(new Dimension(0, 5)));
        lPane.add(genreLb);
        lPane.add(Box.createRigidArea(new Dimension(0, 5)));
        lPane.add(directLb);
        lPane.add(Box.createRigidArea(new Dimension(0, 5)));
        lPane.add(actorLb);
        lPane.add(Box.createRigidArea(new Dimension(0, 5)));
        lPane.add(typeLb);;
        lPane.add(Box.createRigidArea(new Dimension(0, 5)));
        lPane.add(mpaaLb);
        lPane.add(Box.createRigidArea(new Dimension(0, 5)));


        posterPanel.add(rPane, BorderLayout.WEST);
        posterPanel.add(lPane, BorderLayout.CENTER);
    }

    private void setBottomPanelLayout() {
        JPanel ratingPanel = new JPanel();
        BoxLayout ratingLayout = new BoxLayout(ratingPanel, BoxLayout.PAGE_AXIS);
        ratingPanel.setLayout(ratingLayout);
        ratingPanel.add(rateInfoLb);
        ratingPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        ratingPanel.add(starLb);

        JPanel toolPanel = new JPanel();
        BoxLayout toolLayout = new BoxLayout(toolPanel, BoxLayout.LINE_AXIS);
        toolPanel.setLayout(toolLayout);
        toolPanel.add(playLb);

        BoxLayout bottomLayout = new BoxLayout(bottomPanel, BoxLayout.LINE_AXIS);
        bottomPanel.setLayout(bottomLayout);
        bottomPanel.add(ratingPanel);
        bottomPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        bottomPanel.add(toolPanel);

    }
}
