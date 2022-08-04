package com.example.kotlin

import org.jetbrains.annotations.NotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Configuration
import org.springframework.util.Assert
import org.springframework.validation.annotation.Validated

@Configuration
data class Properties(
    @Value("\${null.property}")
    val invalidProperty: String
)

@Validated
@ConstructorBinding
@ConfigurationProperties(prefix = "tryout")
data class ConfigProperties(
    @get:NotNull val anotherInvalidProperty: String
)

@SpringBootTest
@EnableConfigurationProperties(ConfigProperties::class)
class PropertyInjectionTryoutApplicationTests(
    @Autowired val properties: Properties,
    @Autowired val configProperties: ConfigProperties
) {

    @Test
    fun contextLoads() {
        Assert.notNull(properties.invalidProperty, "It is null");
        Assert.notNull(configProperties.anotherInvalidProperty, "It's null")
    }

}
