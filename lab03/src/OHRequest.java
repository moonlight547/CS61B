import java.util.Comparator;

public OHRequest next;
public OHRequest(String description,String name ) {
    this.description = description;
    this.name = name;
    this.isSetup = isSetup;
    this.next = next;

}

public class OHRequestComparator implements Comparator<OHRequest> {

    @Override
    boolean s1DescMatchesSetup = s1.description.euquals("Setup");
    boolean s2DescMatchesSetup = s2.description.euquals("Setup");
    public int compare(OHRequest s1, OHRequest s2) {
        if(s1.isSetup == true && s2.isSetup == false) {
            return -1;
        }
        else if (s1.isSetup == false && s2.isSetup == true) {
            return 1;
        }
        else if(s1DescMatchesSetup && !s2DescMatchesSetup) {
            return -1;
        }
        else if(!s1DescMatchesSetup && s2DescMatchesSetup ) {
            return 1;
        }
        return 0;
    }
}
