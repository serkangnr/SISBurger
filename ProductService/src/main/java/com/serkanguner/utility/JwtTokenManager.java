package com.serkanguner.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.serkanguner.exception.ProductServiceException;
import com.serkanguner.exception.ErrorType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class JwtTokenManager {
    @Value("${productservice.secret.secret-key}")
    String secretKey;
    Long expireTime = 1000L * 60 * 15; //15 dakikalik bir zaman
    @Value("${productservice.secret.secret-issuer}")
    String issuer;

    /**
     * Dikkat!
     * Claim objeleri icindeki degerler herkes tarafindan gorulebilir.
     * O yuzden claimler ile e-mail , password gibi herkesin gormesini istemedigimiz bilgileri payload kisminda tutmaliyiz.
     */


    //1. Token uretmeli
    public Optional<String> createToken(String id) {
        String token = "";
        try {
            token = JWT.create()
                    .withClaim("id", id)
                    .withClaim("whichpage", "AdminService")
                    .withClaim("class", "Java JWT")
                    .withClaim("group", "sisburger")
                    .withIssuer("sisburger")
                    .withIssuedAt(new Date()) // Tokenin yaratildigi an
                    .withExpiresAt(new Date(System.currentTimeMillis() + expireTime))
                    .sign(Algorithm.HMAC512(secretKey));
            return Optional.of(token);
        } catch (IllegalArgumentException e) {
            throw new ProductServiceException(ErrorType.TOKEN_CREATION_FAILED);
        } catch (JWTCreationException e) {
            throw new ProductServiceException(ErrorType.TOKEN_CREATION_FAILED);
        }

    }

/*public Optional<String> createDoubleToken(Long id, Role role){
        String doubleToken = "";
        try {
            doubleToken = JWT.create()
                    .withClaim("id", id)
                    .withClaim("role", role.toString())
                    .withClaim("whichpage", "AuthMicroService")
                    .withClaim("ders", "Java JWT")
                    .withClaim("group", "Java14")
                    .withIssuer("Java14")
                    .withIssuedAt(new Date()) // Tokenin yaratildigi an
                    .withExpiresAt(new Date(System.currentTimeMillis() + expireTime))
                    .sign(Algorithm.HMAC512(secretKey));
            return Optional.of(doubleToken);
        } catch (IllegalArgumentException e) {
            throw new AuthServiceException(ErrorType.TOKEN_CREATION_FAILED);
        } catch (JWTCreationException e) {
            throw new AuthServiceException(ErrorType.TOKEN_CREATION_FAILED);
        }

    }*/


    //2. Token Dogrulanmali
//    public Optional<Long> verifyToken(String token) {
//        try {
//            Algorithm algorithm = Algorithm.HMAC512(secretKey);
//            JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).build();
//            DecodedJWT decodedJWT = verifier.verify(token);
//
//            if (decodedJWT==null) {
//                return Optional.empty();
//            }
//            Long id = decodedJWT.getClaim("id").asLong();
//
//            return Optional.of(id);
//
//        } catch (IllegalArgumentException e) {
//            throw new AuthServiceException((ErrorType.TOKEN_ARGUMENT_NOTVALID));
//        } catch (JWTVerificationException e) {
//            throw new AuthServiceException(ErrorType.TOKEN_VERIFY_FAILED);
//        }
//
//    }

    //3 Tokendan bilgi cakirimi yapmali
    public Optional<String> getIdFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).build();
            DecodedJWT decodedJWT = verifier.verify(token);

            if (decodedJWT == null) {
                return Optional.empty();
            }
            String id = decodedJWT.getClaim("id").asString();

            return Optional.of(id);

        } catch (IllegalArgumentException e) {
            throw new ProductServiceException((ErrorType.TOKEN_ARGUMENT_NOTVALID));
        } catch (JWTVerificationException e) {
            throw new ProductServiceException(ErrorType.TOKEN_VERIFY_FAILED);
        }
    }
    public Optional<String> getNameFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).build();
            DecodedJWT decodedJWT = verifier.verify(token);

            if (decodedJWT == null) {
                return Optional.empty();
            }
            String name = decodedJWT.getClaim("name").asString();

            return Optional.of(name);

        } catch (IllegalArgumentException e) {
            throw new ProductServiceException((ErrorType.TOKEN_ARGUMENT_NOTVALID));
        } catch (JWTVerificationException e) {
            throw new ProductServiceException(ErrorType.TOKEN_VERIFY_FAILED);
        }
    }
    public Optional<String> getPasswordFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).build();
            DecodedJWT decodedJWT = verifier.verify(token);

            if (decodedJWT == null) {
                return Optional.empty();
            }
            String password = decodedJWT.getClaim("password").asString();

            return Optional.of(password);

        } catch (IllegalArgumentException e) {
            throw new ProductServiceException((ErrorType.TOKEN_ARGUMENT_NOTVALID));
        } catch (JWTVerificationException e) {
            throw new ProductServiceException(ErrorType.TOKEN_VERIFY_FAILED);
        }
    }



public Optional<String> getRoleFromToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC512(secretKey);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(issuer).build();
            DecodedJWT decodedJWT = verifier.verify(token);

            if (decodedJWT==null) {
                return Optional.empty();
            }
            String role = decodedJWT.getClaim("role").asString();

            return Optional.of(role);

        } catch (IllegalArgumentException e) {
            throw new ProductServiceException((ErrorType.TOKEN_ARGUMENT_NOTVALID));
        } catch (JWTVerificationException e) {
            throw new ProductServiceException(ErrorType.TOKEN_VERIFY_FAILED);
        }
    }

}
