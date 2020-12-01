package fr.reeter.advent2020.expense;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class that checks the expense report
 */
@Component
public class ExpenseReport {

    private List<String> expenses;

    /**
     * Load the expense input
     * @param expenseReportInput
     * @throws IOException
     */
    public ExpenseReport(@Value("classpath:expense_report.in") final Resource expenseReportInput) throws IOException {
        final String[] input = (Files.readString(expenseReportInput.getFile().toPath())).split(",");
        expenses = Arrays.stream(input).collect(Collectors.toList());
    }

}
