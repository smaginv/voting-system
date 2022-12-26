package ru.smaginv.kvoting.config

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.PropertyAccessor
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter

@Configuration
class AppConfig(
    propertiesConfig: PropertiesConfig
) {

    private val datePattern = propertiesConfig.format().date
    private val dateTimePattern = propertiesConfig.format().dateTime

    @Bean
    fun builderCustomizer(): Jackson2ObjectMapperBuilderCustomizer {
        return Jackson2ObjectMapperBuilderCustomizer { builder ->
            builder.apply {
                serializationInclusion(JsonInclude.Include.NON_NULL)
                serializationInclusion(JsonInclude.Include.NON_EMPTY)
                visibility(PropertyAccessor.IS_GETTER, JsonAutoDetect.Visibility.NONE)
                visibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE)
                visibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                serializers(LocalDateSerializer(DateTimeFormatter.ofPattern(datePattern)))
                serializers(LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimePattern)))
                deserializers(LocalDateDeserializer(DateTimeFormatter.ofPattern(datePattern)))
                deserializers(LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(dateTimePattern)))
                dateFormat(SimpleDateFormat(dateTimePattern))
            }
        }
    }
}