package dev.sol.app;

import java.text.DateFormatSymbols;
import java.util.Locale;

import dev.sol.core.controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;

public class BirthdayStatisticsController extends Controller {
    @FXML
    private BarChart<String, Integer> birthdayChart;
    @FXML
    private CategoryAxis xAxis;

    private ObservableList<String> monthNames;
    Series<String, Integer> birthdayChart_seriesValue;

    @Override
    protected void load_fields() {
        monthNames = FXCollections.observableArrayList(
                DateFormatSymbols.getInstance(Locale.ENGLISH)
                        .getMonths());
        xAxis.setCategories(monthNames);

        monthNames.forEach(month -> {
            int monthCount = (int) app.getPersonlist()
                    .stream()
                    .filter(person -> person.getBirthdate()
                            .getMonth().toString().equals(month.toUpperCase()))
                    .count();
            birthdayChart_seriesValue.getData().add(new Data<>(month, monthCount));
        });

        birthdayChart.getData().add(birthdayChart_seriesValue);
    }

    @Override
    protected void load_bindings() {
    }

    @Override
    protected void load_listeners() {
    }

}
