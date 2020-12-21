/**
 * Jooby https://jooby.io
 * Apache License Version 2.0 https://jooby.io/LICENSE.txt
 * Copyright 2014 Edgar Espina
 */
package io.jooby;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

/**
 * Available environment options.
 *
 * @author edgar
 * @since 2.0.0
 */
public class EnvironmentOptions {
  private static final String ENV = "application.env";
  private String basedir;

  private String filename;

  private ClassLoader classLoader;

  private String[] activeNames;

  /**
   * Creates environment options. Default application file name is: <code>application.conf</code>.
   */
  public EnvironmentOptions() {
    setFilename("application.conf");
  }

  /**
   * Active environment names or fallback and read them from system property:
   * <code>application.env</code>.
   *
   * @return Active environment names.
   */
  public List<String> getActiveNames() {
    return activeNames == null ? defaultEnvironmentNames() : Arrays.asList(activeNames);
  }

  /**
   * Set active environment names.
   *
   * @param activeNames Active environment names.
   * @return This options.
   */
  public @Nonnull EnvironmentOptions setActiveNames(@Nonnull String... activeNames) {
    this.activeNames = activeNames;
    return this;
  }

  /**
   * Set active environment names.
   *
   * @param activeNames Active environment names.
   * @return This options.
   */
  public @Nonnull EnvironmentOptions setActiveNames(@Nonnull List<String> activeNames) {
    this.activeNames = activeNames.toArray(new String[0]);
    return this;
  }

  private static @Nonnull List<String> defaultEnvironmentNames() {
    return Arrays.asList(
        System.getProperty(ENV, System.getenv().getOrDefault(ENV, "dev")).split("\\s*,\\s*"));
  }

  /**
   * Class loader.
   *
   * @return Class loader.
   */
  public @Nonnull ClassLoader getClassLoader() {
    return classLoader == null ? getClass().getClassLoader() : classLoader;
  }

  /**
   * Class loader.
   *
   * @param defaultClassLoader Default classloader is none was set.
   * @return Class loader.
   */
  public @Nonnull ClassLoader getClassLoader(@Nonnull ClassLoader defaultClassLoader) {
    return classLoader == null ? defaultClassLoader : classLoader;
  }

  /**
   * Set class loader.
   *
   * @param classLoader Class loader.
   * @return This options.
   */
  public @Nonnull EnvironmentOptions setClassLoader(@Nonnull ClassLoader classLoader) {
    this.classLoader = classLoader;
    return this;
  }

  /**
   * Base directory to use or <code>null</code> for default.
   *
   * @return Base directory to use or <code>null</code> for default.s
   */
  public @Nullable String getBasedir() {
    return basedir;
  }

  /**
   * Configuration file name.
   *
   * @return Configuration file name.
   */
  public @Nonnull String getFilename() {
    return filename;
  }

  /**
   * Set base dir.
   *
   * @param basedir Base dir. Classpath folder or file system directory.
   * @return This options.
   */
  public @Nonnull EnvironmentOptions setBasedir(@Nullable String basedir) {
    this.basedir = basedir;
    return this;
  }

  /**
   * Set base dir.
   *
   * @param basedir Base dir.
   * @return This options.
   */
  public @Nonnull EnvironmentOptions setBasedir(@Nullable Path basedir) {
    this.basedir = basedir.toAbsolutePath().toString();
    return this;
  }

  /**
   * Set file name.
   *
   * @param filename File name with extension. Supported extensions are: <code>.properties</code>,
   *     <code>.conf</code> and <code>.json</code>.
   * @return This environment.
   */
  public @Nonnull EnvironmentOptions setFilename(@Nonnull String filename) {
    this.filename = filename;
    return this;
  }
}
