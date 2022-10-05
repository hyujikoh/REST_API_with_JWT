이프로젝트는 jwt 를 더 활용한 프로젝트 입니다. 


10/05
1. 프로젝트 생성 

@EnableJpaAuditing

@SuperBuilder
@MappedSuperclass
@NoArgsConstructor(access = PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@ToString


@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")



ResultActions