package src.main.dosw.semana_3.taller4;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.function.Function;

public class Ejercicio10 {

    public static void main(String[] args) {
        ImageEditor editor = new ImageEditor(new BaseImage("foto-producto.png"));
        CommandHistory history = new CommandHistory();

        System.out.println(editor.render());
        history.execute(new ApplyFilterCommand(editor, GrayscaleDecorator::new, "Blanco y negro"));
        history.execute(new ApplyFilterCommand(editor, BrightnessDecorator::new, "Brillo"));
        history.execute(new ApplyFilterCommand(editor, SepiaDecorator::new, "Sepia"));

        System.out.println("Resultado acumulado: " + editor.render());
        history.undo("Brillo");
        System.out.println("Despues de undo individual: " + editor.render());
    }

    interface Image {
        String render();
    }

    static class BaseImage implements Image {
        private final String fileName;

        BaseImage(String fileName) {
            this.fileName = fileName;
        }

        public String render() {
            return "Imagen base " + fileName;
        }
    }

    abstract static class ImageDecorator implements Image {
        protected final Image wrapped;

        ImageDecorator(Image wrapped) {
            this.wrapped = wrapped;
        }
    }

    static class GrayscaleDecorator extends ImageDecorator {
        GrayscaleDecorator(Image wrapped) {
            super(wrapped);
        }

        public String render() {
            return wrapped.render() + " + filtro blanco y negro";
        }
    }

    static class SepiaDecorator extends ImageDecorator {
        SepiaDecorator(Image wrapped) {
            super(wrapped);
        }

        public String render() {
            return wrapped.render() + " + filtro sepia";
        }
    }

    static class BrightnessDecorator extends ImageDecorator {
        BrightnessDecorator(Image wrapped) {
            super(wrapped);
        }

        public String render() {
            return wrapped.render() + " + ajuste de brillo";
        }
    }

    static class ContrastDecorator extends ImageDecorator {
        ContrastDecorator(Image wrapped) {
            super(wrapped);
        }

        public String render() {
            return wrapped.render() + " + ajuste de contraste";
        }
    }

    static class NoiseReductionDecorator extends ImageDecorator {
        NoiseReductionDecorator(Image wrapped) {
            super(wrapped);
        }

        public String render() {
            return wrapped.render() + " + reduccion de ruido";
        }
    }

    static class ImageEditor {
        private final Image baseImage;
        private final List<FilterLayer> filters = new ArrayList<>();

        ImageEditor(Image image) {
            this.baseImage = image;
        }

        Image getImage() {
            return filters.stream()
                    .reduce(
                            baseImage,
                            (current, layer) -> layer.filter().apply(current),
                            (left, right) -> right
                    );
        }

        void addFilter(String name, Function<Image, Image> filter) {
            filters.add(new FilterLayer(name, filter));
        }

        void removeFilter(String name) {
            filters.removeIf(layer -> layer.name().equals(name));
        }

        String render() {
            return getImage().render();
        }
    }

    record FilterLayer(String name, Function<Image, Image> filter) {
    }

    interface ImageCommand {
        void execute();

        void undo();
    }

    static class ApplyFilterCommand implements ImageCommand {
        private final ImageEditor editor;
        private final Function<Image, Image> filter;
        private final String filterName;

        ApplyFilterCommand(ImageEditor editor, Function<Image, Image> filter, String filterName) {
            this.editor = editor;
            this.filter = filter;
            this.filterName = filterName;
        }

        public void execute() {
            editor.addFilter(filterName, filter);
            System.out.println("Filtro aplicado: " + filterName);
        }

        public void undo() {
            editor.removeFilter(filterName);
            System.out.println("Undo filtro: " + filterName);
        }

        boolean matches(String filterName) {
            return this.filterName.equals(filterName);
        }
    }

    static class CommandHistory {
        private final Deque<ImageCommand> executed = new ArrayDeque<>();

        void execute(ImageCommand command) {
            command.execute();
            executed.push(command);
        }

        void undo(String filterName) {
            executed.stream()
                    .filter(command -> command instanceof ApplyFilterCommand applyFilterCommand
                            && applyFilterCommand.matches(filterName))
                    .findFirst()
                    .ifPresent(ImageCommand::undo);
        }
    }
}
