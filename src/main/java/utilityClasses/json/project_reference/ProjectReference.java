package utilityClasses.json.project_reference;

import java.util.ArrayList;
import java.util.List;

public class ProjectReference {
    private List<ProductPackage> allProductPackages;

    private String projectReferenceName;

    public ProjectReference(String projectReferenceName) {
        this.projectReferenceName = projectReferenceName;
        allProductPackages = new ArrayList<>();
    }

    public List<ProductPackage> getAllProductPackages() {
        return allProductPackages;
    }

    public void setAllProductPackages(List<ProductPackage> allProductPackages) {
        this.allProductPackages = allProductPackages;
    }

    public String getProjectReferenceName() {
        return projectReferenceName;
    }
}
