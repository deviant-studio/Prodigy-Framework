package ds.prodigy.apt;

import com.squareup.javapoet.*;

import java.util.List;

import static javax.lang.model.element.Modifier.*;

final class CodeGenerator {

    private ClassName configurationClass = ClassName.get("ds.prodigy", "Configuration");
    List<Config> configs;

    CodeGenerator(List<Config> configs) {
        this.configs = configs;
    }

    TypeSpec generateClass() {
        return TypeSpec.classBuilder("GeneratedConfigs")
                       .addModifiers(PUBLIC, FINAL)
                       .addMethod(provideConfigs())
                       .build();

    }

    private FieldSpec configsField() {
        return FieldSpec.builder(ParameterizedTypeName.get(ClassName.get("java.util", "List"), configurationClass),
                "CONFIGS", PUBLIC, STATIC, FINAL)
                        .initializer("new $T()", ClassName.get("java.util", "ArrayList"))
                        .addJavadoc("Autogenerated configs")
                        .build();
    }

    private CodeBlock initConfigs() {
        CodeBlock.Builder codeBlock = CodeBlock.builder();
        for (Config c : configs) {
            codeBlock.addStatement("CONFIGS.add(new $T($L, $L.class, $L))",
                    configurationClass, c.component, c.presenter, Integer.valueOf(c.layout));

        }
        return codeBlock.build();
    }

    private MethodSpec provideConfigs() {
        ParameterizedTypeName listType = ParameterizedTypeName.get(ClassName.get("java.util", "List"), configurationClass);
        MethodSpec.Builder builder = MethodSpec.methodBuilder("provide")
                                               .addModifiers(PUBLIC, FINAL, STATIC)
                                               .returns(listType);
        builder.addStatement("$T configs = new $T()", listType, ClassName.get("java.util", "ArrayList"));
        for (Config c : configs) {
            builder.addStatement("configs.add(new $T($L, $L.class, $L))",
                    configurationClass, c.component, c.presenter, Integer.valueOf(c.layout));
        }
        return builder.addStatement("return configs")
                      .build();

    }

}
