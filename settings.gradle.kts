pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/Qabylzhaparov/lab2")
            credentials {
                username = System.getenv("GITHUB_USERNAME")  // Используем переменную окружения для имени пользователя
                password = System.getenv("GITHUB_TOKEN")    // Используем переменную окружения для токена
            }
        }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

rootProject.name = "impl"
include(":app")
include(":mylibrary")
