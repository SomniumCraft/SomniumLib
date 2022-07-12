package ru.somniumcraft.somniumlib.Database.jooq.GeneratorStrategies;

import org.jooq.codegen.DefaultGeneratorStrategy;
import org.jooq.meta.Definition;

public class SharedPrefixGeneratorStrategy extends DefaultGeneratorStrategy {
    @Override
    public String getJavaClassName(final Definition definition, final Mode mode) {
        return "Shared" + super.getJavaClassName(definition, mode);
    }
}

