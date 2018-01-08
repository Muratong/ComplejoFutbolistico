/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidadesReservas.Reserva;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author DelKo
 */
@Named(value = "chartBarView")
@ManagedBean
public class ChartBarView {

    @Inject
    ReservaControlador reservaBean;
    private BarChartModel barModel;

    @PostConstruct
    public void init() {
        createBarModel();
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
        ChartSeries series1 = new ChartSeries();
        series1.setLabel("Mis reservas");
        List<Reserva> listaReservas = reservaBean.reservasPorUsuario();
        int pendiente =0;
        int confirmada = 0;
        int tomada =0;
        int cancelada =0;
        int vencida = 0;
        int reservasUsu =  listaReservas.size();

        for (int i = 0; i < reservasUsu; i++) {
            
            if (listaReservas.get(i).getResEstado().getEstResId() == 1) {
                pendiente=pendiente+1;
            } 
            if(listaReservas.get(i).getResEstado().getEstResId() == 2){
                confirmada = confirmada+1;
            }
            if(listaReservas.get(i).getResEstado().getEstResId() == 3){
                tomada = tomada + 1;
            }
            if(listaReservas.get(i).getResEstado().getEstResId() == 4){
               cancelada = cancelada + 1;
            }
            if(listaReservas.get(i).getResEstado().getEstResId() == 5){
               vencida = vencida + 1;
            }
        }
        
        for (int i = 0; i < listaReservas.size(); i++) {
            
            if (listaReservas.get(i).getResEstado().getEstResId() == 1) {
                series1.getData().put("PENDIENTE", (pendiente*100)/reservasUsu);
            } 
            if(listaReservas.get(i).getResEstado().getEstResId() == 2){
                series1.getData().put("CONFIRMADA", (confirmada*100)/reservasUsu);
            }
            if(listaReservas.get(i).getResEstado().getEstResId() == 3){
                series1.getData().put("TOMADA", (tomada*100)/reservasUsu);
            }
            if(listaReservas.get(i).getResEstado().getEstResId() == 4){
                series1.getData().put("CANCELADA", (cancelada*100)/reservasUsu);
            }
            if(listaReservas.get(i).getResEstado().getEstResId() == 5){
                series1.getData().put("VENCIDA", (vencida*100)/reservasUsu);
            }
        }

        model.addSeries(series1);
        return model;
    }

    private void createBarModel() {
        barModel = initBarModel();

        barModel.setTitle("Historial de reservas");
        barModel.setAnimate(true);
        barModel.setLegendPosition("se");
        barModel.setShowPointLabels(true);
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Estado de reservas");

        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Porcentual");
        yAxis.setTickInterval("20");
        yAxis.setMin(0);
        yAxis.setMax(100);
        yAxis.setTickFormat("%d"); //"%d" sin decimales;
        //"%#.1f" float con un decimal.. el 1 se puede reemplazaar por la cant d decimales q quiera
    }

}
