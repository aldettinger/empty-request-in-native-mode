package org.aldettinger;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.aldettinger.CustomPojoExtractionService.CustomPojo;

@Path("/main")
public class MainResource {

    private static final String text = "Operator: Hello, how may I help you ?\nCustomer: Hello, I'm calling because I need to declare an accident on my main vehicle.\nOperator: Ok, can you please give me your name ?\nCustomer: My name is Sarah London.\nOperator: Could you please give me your birth date ?\nCustomer: 1986, July the 10th.\nOperator: Ok, I've got your contract and I'm happy to share with you that we'll be able to reimburse all expenses linked to this accident.\nCustomer: Oh great, many thanks.";
    private static final String dateFormat = "YYYY-MM-DD";

    @Inject
    CustomPojoExtractionService customPojoExtractionService;

    @GET
    public String getCustomPojoName() {
        CustomPojo pojo = customPojoExtractionService.extractFromText(text, dateFormat);
        return pojo.customerName;
    }
}
