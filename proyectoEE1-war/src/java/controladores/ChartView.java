/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import entidadesReservas.Reserva;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.inject.Inject;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author DelKo
 */
@Named(value = "chartView")
@ManagedBean
public class ChartView implements Serializable {

    @Inject
    ReservaControlador reservaBean;
    private LineChartModel model;

    @PostConstruct
    public void init() {
        createAnimatedModels();
    }

    private void createAnimatedModels() {

        model = new LineChartModel();
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Serie 1");
        List<Reserva> listaReservas = reservaBean.reservasPorUsuario();
        System.out.println("NUMERO DE RESERVAS DEL USUARIO: " + listaReservas.size());

        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.US);
        SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy/MM/dd");
        Date miFecha = null;
        String fechaFormateada = null;

        for (int i = 0; i < listaReservas.size(); i++) {

            if (listaReservas.get(i).getResEstado().getEstResId() == 2) {
                try {
                    miFecha = formatoDelTexto.parse(listaReservas.get(i).getResHoraInicio());
                    fechaFormateada = formatoDeFecha.format(miFecha);
                    series1.getData().put(fechaFormateada, 7.5);
                    System.out.println("FECHA: " + fechaFormateada + "PUNTAJE: "+7.5);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            } else {
                try {
                    miFecha = formatoDelTexto.parse(listaReservas.get(i).getResHoraInicio());
                    fechaFormateada = formatoDeFecha.format(miFecha);
                    series1.getData().put(fechaFormateada, 2.5);
                    System.out.println("FECHA: " + fechaFormateada + "PUNTAJE: "+2.5);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
        }

        model.addSeries(series1);
        model.setTitle("Mi reputacion");
        model.setAnimate(true);
        model.setLegendPosition("se");
        //dateModel.setShowPointLabels(true);
        //dateModel.getAxes().put(AxisType.Y, new CategoryAxis("Valoracion"));
        Axis yAxis = model.getAxis(AxisType.Y);
        yAxis.setTickInterval("2.5");
        yAxis.setLabel("Valoracion");
        yAxis.setMin(0);
        yAxis.setMax(10);
        DateAxis axis = new DateAxis("Reservas");
        axis.setTickAngle(-50); // inclinacion del texto
        //axis.setMax("2017-12-01");
        axis.setTickFormat("%#d %b, %y"); // formato
        model.getAxes().put(AxisType.X, axis);
    }

    public LineChartModel getLinearModel() {
        return model;
    }
}
