package br.com.gravitech.condonews.domain.utils;

/**
 * Central location for all application string constants, organized by domain.
 */
public final class StringConstants {

    private StringConstants() {
        // Prevent instantiation
    }

    public static final class Validation {
        private Validation() {}

        public static final String REQUIRED_FIELD = "Campo obrigatório";
        public static final String INVALID_EMAIL = "Email inválido";
        public static final String INVALID_PASSWORD = "Senha deve ter no mínimo 8 caracteres";
    }

    public static final class Exception {
        private Exception() {}

        public static final String USER_NOT_FOUND = "Usuário não encontrado";
        public static final String NEWS_NOT_FOUND = "Notícia não encontrada";
        public static final String CONDO_NOT_FOUND = "Condomínio não encontrado";
        public static final String RESIDENT_NOT_FOUND = "Morador não encontrado";
        public static final String INVALID_TOKEN = "Token inválido";
        public static final String EXPIRED_TOKEN = "Token expirado";
        public static final String BAD_CREDENTIALS = "Credenciais inválidas";
        public static final String BAD_REQUEST = "Requisição com dados inválidos";
    }

    public static final class Log {
        private Log() {}

        public static final String STARTING_METHOD = "Iniciando método {}";
        public static final String ENDING_METHOD = "Finalizando método {}";
        public static final String USER_AUTHENTICATED = "Usuário autenticado: {}";
        public static final String TOKEN_GENERATED = "Token gerado para usuário: {}";

        public static final class News {
            private News() {}
            public static final String CREATING_NEWS = "Criando notícia: {}";
            public static final String UPDATING_NEWS = "Atualizando notícia: {}";
            public static final String DELETING_NEWS = "Excluindo notícia: {}";
            public static final String FINDING_NEWS = "Buscando notícia: {}";
            public static final String FINDING_ALL_NEWS = "Buscando todas as notícias";
            public static final String FINDING_BREAKING_NEWS = "Buscando notícias urgentes";
        }

        public static final class User {
            private User() {}
            public static final String CREATING_USER = "Criando usuário: {}";
            public static final String UPDATING_USER = "Atualizando usuário: {}";
            public static final String DELETING_USER = "Excluindo usuário: {}";
            public static final String FINDING_USER = "Buscando usuário: {}";
        }

        public static final class Condo {
            private Condo() {}
            public static final String CREATING_CONDO = "Criando condomínio: {}";
            public static final String UPDATING_CONDO = "Atualizando condomínio: {}";
            public static final String DELETING_CONDO = "Excluindo condomínio: {}";
            public static final String FINDING_CONDO = "Buscando condomínio: {}";
        }

        public static final class Resident {
            private Resident() {}
            public static final String CREATING_RESIDENT = "Criando morador: {}";
            public static final String UPDATING_RESIDENT = "Atualizando morador: {}";
            public static final String DELETING_RESIDENT = "Excluindo morador: {}";
            public static final String FINDING_RESIDENT = "Buscando morador: {}";
        }

        public static final class Dashboard {
            private Dashboard() {}
            public static final String FETCHING_DASHBOARD = "Buscando dados do dashboard para condomínio: {}";
            public static final String DASHBOARD_TOTAL_NEWS = "Total de notícias: {}";
            public static final String DASHBOARD_BREAKING_NEWS = "Total de notícias urgentes: {}";
            public static final String DASHBOARD_ACTIVE_USERS = "Total de usuários ativos: {}";
        }

        public static final class Error {
            private Error() {}
            public static final String ERROR_BASE = "[ERRO] ";
            public static final String ERROR_CREATING = ERROR_BASE + "Erro ao criar: {}";
            public static final String ERROR_UPDATING = ERROR_BASE + "Erro ao atualizar: {}";
            public static final String ERROR_DELETING = ERROR_BASE + "Erro ao excluir: {}";
            public static final String ERROR_FINDING = ERROR_BASE + "Erro ao buscar: {}";
        }

        public static final class Auth {
            private Auth() {}
            public static final String AUTHENTICATING_USER = "Autenticando usuário: {}";
            public static final String USER_INACTIVE = "Usuário inativo: {}";
            public static final String INVALID_CREDENTIALS = "Credenciais inválidas para usuário: {}";
            public static final String REFRESHING_TOKEN = "Atualizando token para usuário: {}";
            public static final String LOGGING_OUT = "Realizando logout do usuário";
        }
    }

    public static final class Success {
        private Success() {}

        public static final String NEWS_CREATED = "Notícia criada com sucesso";
        public static final String NEWS_UPDATED = "Notícia atualizada com sucesso";
        public static final String NEWS_DELETED = "Notícia excluída com sucesso";
        public static final String USER_CREATED = "Usuário criado com sucesso";
        public static final String RESIDENT_CREATED = "Morador criado com sucesso";
    }

    public static final class Security {
        private Security() {}

        public static final String TOKEN_TYPE = "Bearer";
        public static final String REFRESH_TOKEN_COOKIE = "refreshToken";
        public static final String AUTHORIZATION_HEADER = "Authorization";
    }

    public static final class Api {
        private Api() {}

        public static final String BASE_PATH = "/api/v1";
        public static final String AUTH_PATH = BASE_PATH + "/auth";
        public static final String NEWS_PATH = BASE_PATH + "/news";
        public static final String CONDO_PATH = BASE_PATH + "/condos";
        public static final String RESIDENT_PATH = BASE_PATH + "/residents";
    }
}
