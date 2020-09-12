package com.connect_us.backend.service.cart;

import com.connect_us.backend.domain.account.Account;
import com.connect_us.backend.domain.account.AccountRepository;
import com.connect_us.backend.domain.cart.Cart;
import com.connect_us.backend.domain.cart.CartItem;
import com.connect_us.backend.domain.cart.CartItemRepository;
import com.connect_us.backend.domain.cart.CartRepository;
import com.connect_us.backend.domain.product.Product;
import com.connect_us.backend.domain.product.ProductRepository;
import com.connect_us.backend.web.dto.v1.cart.CartItemAddResponseDto;
import com.connect_us.backend.web.dto.v1.cart.CartItemAddResquestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CartService {
    private final ProductRepository productRepository;
    private final AccountRepository accountRepository;
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;

    // 카트 생성 메서드
    public Cart create(Account account) {
        return Cart.builder()
                .account(account)
                .build();
    }

    // 카트에 물품 추가 
    public CartItemAddResponseDto add(String accountEmail, CartItemAddResquestDto resquestDto) {
        Account account = accountRepository.findByEmail(accountEmail);
        Cart cart = account.getCart();

        Product product = productRepository.findById(resquestDto.getId())
                .orElseThrow(()-> new IllegalArgumentException("해당 상품이 없습니다. id=" + resquestDto.getId()));

        CartItem cartItem = CartItem.builder()
                .cart(cart)
                .product(product)
                .productCnt(resquestDto.getProductCnt())
                .build();

        return CartItemAddResponseDto.builder()
                .success(true)
                .message("장바구니에 상품이 추가되었습니다.")
                .build();
    }

    // 카트 물품 목록 조회
    public void get(String accountEmail) {

    }

    // 카트 삭제
    public void delete(String accountEmail, Long id){

    }
}
