package src.main.dosw.semana_3.taller4;

public class Ejercicio04 {

    public static void main(String[] args) {
        GameCharacter warrior = new GameCharacterBuilder()
                .setType("Guerrero")
                .setArmor("Armadura de acero")
                .setWeapon("Espada larga")
                .setSkill("Furia")
                .build();

        GameCharacter powered = new InvisibilityDecorator(new SpeedDecorator(new ShieldDecorator(warrior)));

        System.out.println(warrior.getDescription());
        System.out.println(powered.getDescription());
        powered.attack();
    }

    interface GameCharacter {
        String getDescription();

        void attack();
    }

    static class BasicGameCharacter implements GameCharacter {
        private final String type;
        private final String armor;
        private final String weapon;
        private final String skill;

        BasicGameCharacter(String type, String armor, String weapon, String skill) {
            this.type = type;
            this.armor = armor;
            this.weapon = weapon;
            this.skill = skill;
        }

        public String getDescription() {
            return type + " con " + armor + ", " + weapon + " y habilidad " + skill;
        }

        public void attack() {
            System.out.println(type + " ataca con " + weapon + " usando " + skill);
        }
    }

    static class GameCharacterBuilder {
        private String type = "Aventurero";
        private String armor = "armadura ligera";
        private String weapon = "daga";
        private String skill = "concentracion";

        GameCharacterBuilder setType(String type) {
            this.type = type;
            return this;
        }

        GameCharacterBuilder setArmor(String armor) {
            this.armor = armor;
            return this;
        }

        GameCharacterBuilder setWeapon(String weapon) {
            this.weapon = weapon;
            return this;
        }

        GameCharacterBuilder setSkill(String skill) {
            this.skill = skill;
            return this;
        }

        GameCharacter build() {
            return new BasicGameCharacter(type, armor, weapon, skill);
        }
    }

    abstract static class CharacterDecorator implements GameCharacter {
        protected final GameCharacter wrapped;

        CharacterDecorator(GameCharacter wrapped) {
            this.wrapped = wrapped;
        }
    }

    static class ShieldDecorator extends CharacterDecorator {
        ShieldDecorator(GameCharacter wrapped) {
            super(wrapped);
        }

        public String getDescription() {
            return wrapped.getDescription() + " + escudo de hielo";
        }

        public void attack() {
            System.out.println("Escudo de hielo activo.");
            wrapped.attack();
        }
    }

    static class SpeedDecorator extends CharacterDecorator {
        SpeedDecorator(GameCharacter wrapped) {
            super(wrapped);
        }

        public String getDescription() {
            return wrapped.getDescription() + " + velocidad extra";
        }

        public void attack() {
            System.out.println("Velocidad extra activa.");
            wrapped.attack();
        }
    }

    static class InvisibilityDecorator extends CharacterDecorator {
        InvisibilityDecorator(GameCharacter wrapped) {
            super(wrapped);
        }

        public String getDescription() {
            return wrapped.getDescription() + " + invisibilidad";
        }

        public void attack() {
            System.out.println("Invisibilidad activa antes del ataque.");
            wrapped.attack();
        }
    }
}
