

public class AstroObjeto implements Comparable<AstroObjeto> {
    int neo_id;
    String name;
    double absolute_magnitude, estimated_diameter_min, estimated_diameter_max;
    String orbiting_body;
    double relative_velocity, miss_distance;
    boolean is_hazardous;



    public AstroObjeto(int neo_id, String name, double absolute_magnitude, double estimated_diameter_min, double estimated_diameter_max, String orbiting_body, double relative_velocity, double miss_distance, boolean is_hazardous) {
        this.neo_id = neo_id;
        this.name = name;
        this.absolute_magnitude = absolute_magnitude;
        this.estimated_diameter_max = estimated_diameter_max;
        this.estimated_diameter_min = estimated_diameter_min;
        this.orbiting_body = orbiting_body;
        this.relative_velocity = relative_velocity;
        this.miss_distance = miss_distance;
        this.is_hazardous = is_hazardous;

    }

    public int getId() { 
        return neo_id; }

    public String getNome() { 
        return name; }

    public double getMagnitude() { 
        return absolute_magnitude; }

    public double getDiametro_min() { 
        return estimated_diameter_min; }

    public double getDiametro_max() { 
        return estimated_diameter_max; }

    public String getCorpo_orbitante() { 
        return orbiting_body; }

    public double getVelocidade_relativa() { 
        return relative_velocity; }

    public double getDistancia_perigosa() { 
        return miss_distance; }

    public boolean getPericulosidade() { 
        return is_hazardous; }

    
    // Implementação do método compareTo para comparar objetos AstroObjeto com base no neo_id
    @Override
    public int compareTo(AstroObjeto other) {
        return Double.compare(this.neo_id, other.neo_id);
    }

}
