/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.app.gui;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.esprit.app.entity.Reclam;
import com.esprit.app.services.ServiceReclam;
import java.util.ArrayList;

/**
 *
 * @author barki
 */
public class StatReclam {
      ArrayList<Reclam> mat;
    int idu=1;

  
    
    public float calcul_nbr_rec(ArrayList<Reclam> r,String ch){
        
         ArrayList<Reclam> mat = new ArrayList<Reclam>();
         mat =     ServiceReclam.getInstance().getReclamUser(idu);

        
    int f=0;
    for(int i=0;i<mat.size();i++){
        if (mat.get(i).getEtatrec().equals(ch)){ f++;}
    }
    return f;
}
    
    private DefaultRenderer buildCategoryRenderer(int[] colors) {
    DefaultRenderer renderer = new DefaultRenderer();
    renderer.setLabelsTextSize(40);
    renderer.setLegendTextSize(60);
    
    renderer.setMargins(new int[]{12, 14, 11, 10, 19,0});
    for (int color : colors) {
        SimpleSeriesRenderer r = new SimpleSeriesRenderer();
        r.setColor(color);
        renderer.addSeriesRenderer(r);
    }
    return renderer;
}

/**
 * Builds a category series using the provided values.
 *
 * @param titles the series titles
 * @param values the values
 * @return the category series
 */
protected CategorySeries buildCategoryDataset(String title, double[] values) {
    CategorySeries series = new CategorySeries(title);
        series.add("En attente", this.calcul_nbr_rec(mat, "En attente") );
        series.add("Traitée", this.calcul_nbr_rec(mat, "Traitée") );
         series.add("En cours", this.calcul_nbr_rec(mat, "En cours") );
          
    return series;
    
}

public Form createPieChartForm(Form previous) {
    
    
    
     new Label("Statistiques réclamations");
     
    // Generate the values
    double[] values = new double[]{
        this.calcul_nbr_rec(mat, "En attente"),
        this.calcul_nbr_rec(mat, "Traitée"),
        this.calcul_nbr_rec(mat, "En cours"),
       
        };


    // Set up the renderer
    int[] colors = new int[]{ColorUtil.CYAN, ColorUtil.GREEN, ColorUtil.YELLOW,ColorUtil.MAGENTA};
    DefaultRenderer renderer = buildCategoryRenderer(colors);
    
    renderer.setZoomButtonsVisible(true);
    renderer.setZoomEnabled(true);
    renderer.setChartTitleTextSize(10);
    renderer.setDisplayValues(true);
    renderer.setShowLabels(true);
    renderer.setLabelsColor(1);
    
    
    SimpleSeriesRenderer r = renderer.getSeriesRendererAt(1);
    r.setGradientEnabled(true);
    r.setGradientStart(0, ColorUtil.MAGENTA);
    r.setGradientStop(0, ColorUtil.YELLOW);
    r.setHighlighted(true); 
    
    SimpleSeriesRenderer s = renderer.getSeriesRendererAt(0);
    s.setGradientEnabled(true);
    s.setGradientStart(0, ColorUtil.CYAN);
    s.setGradientStop(0, ColorUtil.YELLOW);
    s.setHighlighted(true);
    
    SimpleSeriesRenderer y = renderer.getSeriesRendererAt(2);
    y.setGradientEnabled(true);
    y.setGradientStart(0, ColorUtil.YELLOW);
    y.setGradientStop(0, ColorUtil.YELLOW);
    y.setHighlighted(true);
    

    // Create the chart ... pass the values and renderer to the chart object.
    PieChart chart = new PieChart(buildCategoryDataset("Calandrier_ex", values), renderer);

    // Wrap the chart in a Component so we can add it to a form
    ChartComponent c = new ChartComponent(chart);

    // Create a form and show it.
    Form f = new Form("Stats of my reclams", new BorderLayout());
    f.addComponent(BorderLayout.CENTER, c);
            //hi.addComponent(BorderLayout.CENTER, clock);

        f.show();

        f.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack());
   /* f.getToolbar().addCommandToOverflowMenu("Retour", null, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
              Gestcalan f2 = new Gestcalan();
              f2.show();
            }
        });*/
return f;
    
    

}
    
}
