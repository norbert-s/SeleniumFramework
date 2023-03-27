package utilityClasses.json.project_reference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class SimpleExample
{
    public static void main(String[]args ) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String,String> mappedJson= mapper.readValue(new File("src/main/java/resources/mapper.json"), Map.class);
        System.out.println(mappedJson);
        List<ProjectReference> allProjectReferences = new ArrayList<>();
        for(int i=0;i<5;i++){
            ProjectReference pr = new ProjectReference("pr "+(i+1));
            int finalI = i;
            mappedJson.forEach((mapKey, mapValue)->{
                ProductPackage pp = new ProductPackage(mapValue);
                pp.setBasePrice("bp "+ finalI);
                pp.setYear1Price("y1p "+ finalI);
                pp.setYear2Price("y2p "+finalI);
                pr.getAllProductPackages().add(pp);
            });
            allProjectReferences.add(pr);
        }
        
        ObjectMapper mapper2 = new ObjectMapper();
        String valami = mapper2.writeValueAsString(allProjectReferences);
        System.out.println(valami);
        ArrayNode valami2 = mapper2.createArrayNode();
        List<String> projectNames = new ArrayList<>();
    }
}
