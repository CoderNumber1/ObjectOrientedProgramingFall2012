

import java.awt.Dimension;
import java.util.ArrayList;
import org.w3c.dom.Element;

public class WeaponManager implements FieldObserver, Iterable<WeaponTypeIterator> {
    private static final WeaponManager _INSTANCE = new WeaponManager();
    private ArrayList<WeaponType> weaponTypes;
    
    public static WeaponManager getInstance(){
        return WeaponManager._INSTANCE;
    }
    private WeaponManager(){
        this.weaponTypes = new ArrayList<WeaponType>();
    }
    
    public WeaponType getWeaponTypeByName(String name){
        for(WeaponType weapon : this.weaponTypes){
            if(weapon.name.equals(name)){
                return weapon;
            }
        }
        
        return null;
    }
    
    public ArrayList<WeaponType> getWeaponTypesByLevel(int level){
        ArrayList<WeaponType> result = new ArrayList<WeaponType>();
        
        for(WeaponType weapon : this.weaponTypes){
            if(weapon.level == level){
                result.add(weapon);
            }
        }
        
        return result;
    }

    @Override
    public void fieldLoaded(Field field) {
        this.weaponTypes.removeAll(this.weaponTypes);
        ArrayList<Element> weapons = field.getFieldReader().getElements("weapons", "weapon");
        
        for(Element weapon : weapons){
            String name = weapon.getElementsByTagName("name").item(0).getTextContent();
            
            boolean defaultWeapon = weapon.hasAttribute("default");
            
            Element size = (Element)weapon.getElementsByTagName("size").item(0);
            Dimension weaponSize = new Dimension();
            weaponSize.height = Integer.parseInt(size.getAttribute("height"));
            weaponSize.width = Integer.parseInt(size.getAttribute("width"));
            
            int speed = Integer.parseInt(weapon.getElementsByTagName("speed").item(0).getTextContent());
            int damage = Integer.parseInt(weapon.getElementsByTagName("damage").item(0).getTextContent());
            int level = Integer.parseInt(weapon.getElementsByTagName("weaponlevel").item(0).getTextContent());
            
            String imageName = null;
            if(weapon.getElementsByTagName("imagename").getLength() > 0){
                imageName = weapon.getElementsByTagName("imagename").item(0).getTextContent();
            }
            
            this.weaponTypes.add(new WeaponType(name, weaponSize, level, damage, speed, imageName, defaultWeapon));
        }
    }

    @Override
    public WeaponTypeIterator getIterator() {
        WeaponManagerIterator result = new WeaponManagerIterator(this.getWeaponTypesByLevel(0));
        
        return result;
    }

    public class WeaponType{
        public WeaponType(String name, Dimension size, int level, int damage, int speed, String imageName, boolean defaultWeapon){
            this.name = name;
            this.size = size;
            this.level = level;
            this.damage = damage;
            this.speed = speed;
            this.imageName = imageName;
            this.defaultWeapon = defaultWeapon;
        }
        
        public final String name;
        public final boolean defaultWeapon;
        public final Dimension size;
        public final int level;
        public final int damage;
        public final int speed;
        public final String imageName;
    }
    
    public class WeaponManagerIterator implements WeaponTypeIterator{
        private ArrayList<WeaponType> types;
        private int index;
        
        public WeaponManagerIterator(ArrayList<WeaponType> types){
            this.types = types;
            this.index = 0;
        }
        
        @Override
        public boolean hasNext() {
            return this.index < this.types.size();
        }

        @Override
        public WeaponType Next() {
            if(this.hasNext()){
                return this.types.get(index++);
            }
            else{
                return null;
            }
        }
        
    }
}
