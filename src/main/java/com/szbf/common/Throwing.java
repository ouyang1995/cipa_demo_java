package com.szbf.common;






import javax.validation.constraints.NotNull;
import java.util.function.Consumer;

public class Throwing {

    private Throwing() {}

   // @NotNull
    public static <T> Consumer<T> rethrow(@NotNull final ThrowingConsumer<T> consumer) {
        return consumer;
    }

    @SuppressWarnings("unchecked")
  //  @NotNull
    public static <E extends Throwable> void sneakyThrow(@NotNull final Throwable ex) throws E {
        throw (E)ex;
    }
}
