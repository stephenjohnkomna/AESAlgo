import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

@Data
public class AccountDetails {
    @CsvBindByPosition(position = 0)
    private String recipient_bank_account_no_encrypted;

    @Override
    public String toString() {
        return "AccountDetails{" +
                "recipient_bank_account_no_encrypted='" + recipient_bank_account_no_encrypted + '\'' +
                ", recipient_bank_account_no_desensitized='" + recipient_bank_account_no_desensitized + '\'' +
                '}';
    }

    @CsvBindByPosition(position = 1)
    private String recipient_bank_account_no_desensitized;
}
