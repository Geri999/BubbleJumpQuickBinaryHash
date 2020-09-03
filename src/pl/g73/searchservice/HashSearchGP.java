package pl.g73.searchservice;

import pl.g73.sortservice.PBDataBase;
import pl.g73.sortservice.PhoneBookRecord;

public class HashSearchGP {
    public static int hashSearchGP(PBDataBase pbDataBase, String[] findDataArray) {
        int counter = 0;
        PhoneBookRecord[] phoneBookArray = pbDataBase.getPhoneBookArray();

        for (String searchingName : findDataArray) {
//            int keyIndexSearchingName = pbDataBase.findKeyIndex(new PhoneBookRecord(searchingName.trim(), ""));
            int keyIndexSearchingName = pbDataBase.findKeyIndex2(searchingName);

            if (phoneBookArray[keyIndexSearchingName] != null) {
                counter++;
            }
        }
        return counter;
    }
}
