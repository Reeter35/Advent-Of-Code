package fr.reeter.advent2020.expense;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private Logger logger;

    private List<String> expenses;

    /**
     * Load the expense input
     * @param expenseReportInput
     * @throws IOException
     */
    public ExpenseReport(@Value("classpath:expense_report.in") final Resource expenseReportInput) throws IOException {
        expenses = (Files.readAllLines(expenseReportInput.getFile().toPath()));
    }


    /**
     * Checks the report (find the couple that adds as 2020)
     * @return
     */
    public int checkReport() {
        for(String exp: expenses) {
            int expInt = Integer.parseInt(exp);
            for(String exp2: expenses) {
                int exp2Int = Integer.parseInt(exp2);
                if(expInt+exp2Int == 2020) {
                    logger.info("Found: " + exp + " / " + exp2);
                    return expInt*exp2Int;
                }
            }
        }

        return -1;
    }

}
