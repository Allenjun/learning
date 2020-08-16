package com.allen.learningbootbatch.config;

import com.allen.learningbootbatch.pojo.DTO.Person;
import java.util.HashMap;
import javax.sql.DataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Bean
    public FlatFileItemReader<Person> flatFileItemReader() {
        FlatFileItemReader<Person> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("sample-data.csv"));
        reader.setLineMapper(new DefaultLineMapper<Person>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames("firstName", "lastName");
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {{
                setTargetType(Person.class);
            }});
        }});
        return reader;
    }

    @Bean
    public JdbcPagingItemReader<Person> jdbcPagingItemReader(DataSource dataSource) {
        JdbcPagingItemReader<Person> reader = new JdbcPagingItemReader<>();
        reader.setDataSource(dataSource);
        reader.setFetchSize(100);

        reader.setQueryProvider(new MySqlPagingQueryProvider() {{
            setSelectClause("SELECT person_id,first_name,last_name");
            setFromClause("from people");
            setWhereClause("last_name=:lastName");
            setSortKeys(new HashMap<String, Order>() {{
                put("person_id", Order.ASCENDING);
            }});
        }});
        reader.setParameterValues(new HashMap<String, Object>() {{
            put("lastName", "DOE");
        }});
        reader.setRowMapper(new BeanPropertyRowMapper<>(Person.class));
        return reader;
    }

    @Bean
    public JdbcBatchItemWriter<Person> jdbcBatchItemWriter(DataSource dataSource) {
        JdbcBatchItemWriter<Person> writer = new JdbcBatchItemWriter<>();
        writer
            .setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        writer.setSql("INSERT INTO people (first_name, last_name) VALUES (:firstName, :lastName)");
        writer.setDataSource(dataSource);
        return writer;
    }

    @Bean
    public Job importUserJob(JobBuilderFactory jobBuilderFactory,
        JobExecutionListener jobCompletionNotificationListener,
        Step step) {
        return jobBuilderFactory.get("importUserJob")
            .incrementer(new RunIdIncrementer())
            .listener(jobCompletionNotificationListener)
            .start(step)
            .build();
    }

    @Bean
    public Step step1(StepBuilderFactory stepBuilderFactory, ItemProcessor personItemProcessor,
        ItemWriter jdbcBatchItemWriter, ItemReader flatFileItemReader) {
        return stepBuilderFactory.get("step1")
            .<Person, Person>chunk(10)
            .reader(flatFileItemReader)
            .processor(personItemProcessor)
            .writer(jdbcBatchItemWriter)
            .build();
    }

    @Bean
    public ItemProcessor personItemProcessor() {
        return null;
    }

    @Bean
    public JobExecutionListener jobCompletionNotificationListener() {
        return null;
    }

}
