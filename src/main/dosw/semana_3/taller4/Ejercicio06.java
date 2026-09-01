package src.main.dosw.semana_3.taller4;

import java.util.ArrayList;
import java.util.List;

public class Ejercicio06 {

    public static void main(String[] args) {
        RecommendationEngine engine = new RecommendationEngine(new GenreStrategy());
        UserProfile profile = new UserProfile("Laura", engine);

        profile.addObserver(new HomePageComponent());
        profile.addObserver(new NotificationService());
        profile.addObserver(new SuggestedListComponent());

        profile.changePreference("fantasia", new GenreStrategy());
        profile.changePreference("tendencias", new PopularityStrategy());
    }

    interface RecommendationAlgorithm {
        List<String> recommend(UserProfile profile);
    }

    static class GenreStrategy implements RecommendationAlgorithm {
        public List<String> recommend(UserProfile profile) {
            return List.of("Serie de fantasia", "Aventura epica", "Mundo magico");
        }
    }

    static class HistoryStrategy implements RecommendationAlgorithm {
        public List<String> recommend(UserProfile profile) {
            return List.of("Basado en historial 1", "Basado en historial 2");
        }
    }

    static class PopularityStrategy implements RecommendationAlgorithm {
        public List<String> recommend(UserProfile profile) {
            return List.of("Top global", "Estreno popular", "Mas visto hoy");
        }
    }

    static class SimilarityStrategy implements RecommendationAlgorithm {
        public List<String> recommend(UserProfile profile) {
            return List.of("Usuarios similares vieron A", "Usuarios similares vieron B");
        }
    }

    static class RecommendationEngine {
        private RecommendationAlgorithm algorithm;

        RecommendationEngine(RecommendationAlgorithm algorithm) {
            this.algorithm = algorithm;
        }

        void setAlgorithm(RecommendationAlgorithm algorithm) {
            this.algorithm = algorithm;
        }

        List<String> recommend(UserProfile profile) {
            return algorithm.recommend(profile);
        }
    }

    interface PreferenceObserver {
        void onPreferenceChanged(UserProfile profile);
    }

    static class HomePageComponent implements PreferenceObserver {
        public void onPreferenceChanged(UserProfile profile) {
            System.out.println("Home actualiza: " + profile.recommend());
        }
    }

    static class NotificationService implements PreferenceObserver {
        public void onPreferenceChanged(UserProfile profile) {
            System.out.println("Notificacion enviada a " + profile.getName());
        }
    }

    static class SuggestedListComponent implements PreferenceObserver {
        public void onPreferenceChanged(UserProfile profile) {
            System.out.println("Sugeridos actualizados: " + profile.recommend());
        }
    }

    static class UserProfile {
        private final String name;
        private final RecommendationEngine engine;
        private final List<PreferenceObserver> observers = new ArrayList<>();
        private String preference;

        UserProfile(String name, RecommendationEngine engine) {
            this.name = name;
            this.engine = engine;
        }

        String getName() {
            return name;
        }

        void addObserver(PreferenceObserver observer) {
            observers.add(observer);
        }

        void changePreference(String preference, RecommendationAlgorithm algorithm) {
            this.preference = preference;
            engine.setAlgorithm(algorithm);
            System.out.println("Preferencia cambiada a " + this.preference);
            observers.forEach(observer -> observer.onPreferenceChanged(this));
        }

        List<String> recommend() {
            return engine.recommend(this);
        }
    }
}
