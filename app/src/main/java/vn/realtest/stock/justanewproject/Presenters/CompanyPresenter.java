package vn.realtest.stock.justanewproject.Presenters;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import vn.realtest.stock.justanewproject.Models.Company;
import vn.realtest.stock.justanewproject.Utils.DataFetchingBackgroundJob;

/**
 * Created by Tran on 28-Jan-18.
 */

public abstract class CompanyPresenter extends DataFetchingBackgroundJob{

    public CompanyPresenter(String dataURL) {
        super(dataURL);
    }

    @Override
    public void OnPostExecute(String result) {
        ArrayList<Company> companies = new ArrayList<>();
        if (result != null && result.length() > 0) {
            Pattern pattern = Pattern.compile("\"(.*?)\"");
            Matcher matcher = pattern.matcher(result);
            while (matcher.find()) {
                String companyString = matcher.group(1);

                if (companyString.length() > 0) {
                    String[] companyInfo = companyString.split("-");

                    if (companyInfo.length > 1) {
                        Company company = new Company();
                        company.setID(companyInfo[0]);
                        company.setName(companyInfo[1]);

                        companies.add(company);
                    }
                }
            }
        }
        OnCompanyModel(companies);
    }

    public abstract void OnCompanyModel(ArrayList<Company> companies);

}
