package sample;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public LineChart chart;
    public Button runBtn;
    public Pane seletor1Pane;
    public ComboBox funcCB;
    public ComboBox methCB;
    public Label numericalResLabel;
    public Label errorLabel;
    public Label trueResLabel;
    public TextField nTB;

    Function func;
    Method meth;
    int n;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        seletor1Pane.setVisible(true);
        funcCB.getItems().setAll("Осциллятор 1", "Осциллятор 2");
        methCB.getItems().setAll("Прямоугольник", "Трапеция", "Симпсон");
    }

    public void solve(ActionEvent actionEvent) {
        chart.getData().clear();
        double trueValue;

        if (funcCB.getValue() == "Осциллятор 2") { func = new OscFunction2(); trueValue = 0.996948667568692;}
        else { func = new OscFunction1(); trueValue = 1.04628712224653; }
        if (methCB.getValue()=="Трапеция") meth = new TrapezoidalRule();
        else if (methCB.getValue()=="Симпсон") meth = new SimpsonsRule();
        else meth = new RiemanSum();

        n = Integer.parseInt(nTB.getText());
        ArrayList<Double> x = new ArrayList<>(n);
        ArrayList<Double> y = new ArrayList<>(n);
        for (int i = 0; i < n; i++) { x.add(1.0+i*(Math.PI*0.5-1.0)/n); y.add(func.getValue(x.get(i))); }

        XYChart.Series series = new XYChart.Series();
        for (int i = 0; i < n; i++){
           series.getData().add(new XYChart.Data(x.get(i).toString(),y.get(i)));
        }
        chart.getData().add(series);

        double res = meth.integrate(func, 1.0, Math.PI*0.5, n);
        numericalResLabel.setText("Численное: "+res);
        trueResLabel.setText("Точное: " + trueValue);
        errorLabel.setText("Ошибка: " + Math.abs(res-trueValue));
    }
}
